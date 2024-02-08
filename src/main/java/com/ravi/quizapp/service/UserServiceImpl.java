package com.ravi.quizapp.service;


import java.util.HashSet;
import java. util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.Role;
import com.ravi.quizapp.entity.User;
import com.ravi.quizapp.entity.UserRole;
import com.ravi.quizapp.exception.UserAlreadyExistException;
import com.ravi.quizapp.exception.UserNotFoundException;
import com.ravi.quizapp.repo.RoleRepository;
import com.ravi.quizapp.repo.UserRepository;
import com.ravi.quizapp.request.UserRequest;
import com.ravi.quizapp.response.UserResponse;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// Creating user
	@Override
	public UserResponse createUser(UserRequest request) {
		
		UserResponse response = new UserResponse();
		
		User user = new User();
		user.setAcive_SW('N');
		BeanUtils.copyProperties(request, user);
		user.setProfile("default.jpg");
		
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
			throw new UserAlreadyExistException("User Already Exist !!");
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
	public UserResponse getUser(String userName) {
		UserResponse response = new UserResponse();
		Optional<User> userRecord = userRepository.findByUserName(userName);
		if(userRecord.isEmpty()) {
			throw new UserNotFoundException("User Not Exist!!");
		}else {
			User user = userRecord.get();
			BeanUtils.copyProperties(user, response);
		}
		return response;
	}


	// Deleting User.
	@Override
	public String deleteUser(String userName) {
		Optional<User> userRecord = userRepository.findByUserName(userName);
		if(userRecord.isEmpty()) {
			throw new UserNotFoundException("User Not Exist!!");
		}else {
			User user = userRecord.get();
			userRepository.deleteById(user.getUserId());
			return "User Deleted!!";
		}
	}

}
