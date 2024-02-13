package com.ravi.quizapp.service;


import java.util.HashSet;
import java. util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.Role;
import com.ravi.quizapp.entity.User;
import com.ravi.quizapp.entity.UserRole;
import com.ravi.quizapp.exception.UserAlreadyExistException;
import com.ravi.quizapp.exception.UserNotFoundException;
import com.ravi.quizapp.repo.RoleRepository;
import com.ravi.quizapp.repo.UserRepository;
import com.ravi.quizapp.request.RegisterRequest;
import com.ravi.quizapp.response.RegisterResponse;
	
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Creating user
	@Override
	public RegisterResponse createUser(RegisterRequest request) {
		
		RegisterResponse response = new RegisterResponse();
		
		User user = new User();
		user.setAcive_SW('Y');
		BeanUtils.copyProperties(request, user);
		user.setProfile("default.jpg");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role = new Role();
		role.setRoleType("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);
		
		// checking user already exist.
		Optional<User> userDetails = userRepository.findByUserEmail(user.getUserEmail());
		if(!(userDetails.isEmpty())) {
			throw new UserAlreadyExistException("User Already Exist with Name "+userDetails.get().getUserName());
		}else {
			for(UserRole roles : userRoles) {
				roleRepository.save(roles.getRole());
			}
			user.setUserRoles(userRoles);
			user = userRepository.save(user);
			BeanUtils.copyProperties(user, response);
		}	
		return response;
	}

	
	// Getting User by name.
	@Override
	public RegisterResponse getUser(String userName) {
		RegisterResponse response = new RegisterResponse();
		Optional<User> userRecord = userRepository.findByUserName(userName);
		if(userRecord.isEmpty()) {
			throw new UserNotFoundException("User Not Found with Name "+userName);
		}else {
			User user = userRecord.get();
			Set<UserRole> userRoles = user.getUserRoles();
			
			// Setting Authority.
			Set<String> authority = new HashSet<>();
			userRoles.forEach(role -> {
				authority.add(role.getRole().getRoleType());
			});
			BeanUtils.copyProperties(user, response);
			response.setAuthority(authority);
		}
		return response;
	}


	// Deleting User.
	@Override
	public String deleteUser(String userName) {
		Optional<User> userRecord = userRepository.findByUserName(userName);
		if(userRecord.isEmpty()) {
			throw new UserNotFoundException("User Not Exist with Name "+userName);
		}else {
			User user = userRecord.get();
			userRepository.deleteById(user.getUserId());
			return "User Deleted!!";
		}
	}
	
	// Loading userName, password and authority from DB.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByUserName(username);
		if(result.isEmpty()) {
			throw new UserNotFoundException("User Not Found with Name "+result.get().getUserName());
		}else {
			User user = result.get();
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(), 
					user.getUserRoles().stream()
						.map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleType()))
						.collect(Collectors.toList()));
		}
	}

}
