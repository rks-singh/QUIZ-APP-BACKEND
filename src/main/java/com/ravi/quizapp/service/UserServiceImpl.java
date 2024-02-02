package com.ravi.quizapp.service;

import java. util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.User;
import com.ravi.quizapp.entity.UserRole;
import com.ravi.quizapp.exception.UserAlreadyExistException;
import com.ravi.quizapp.repo.RoleRepository;
import com.ravi.quizapp.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	

	@Override
	public User saveUser(User user, Set<UserRole> userRoles) {
		
		// check user already exist.
		Optional<User> userDetails = userRepository.findByUserEmail(user.getUserEmail());
		if(!(userDetails.isEmpty())) {
			throw new UserAlreadyExistException("User Already Exist !!");
		}else {
			for(UserRole roles : userRoles) {
				roleRepository.save(roles.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			user = userRepository.save(user);
		}
		
		return user;
	}

}
