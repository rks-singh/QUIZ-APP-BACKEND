package com.ravi.quizapp.restcontroller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.entity.User;
import com.ravi.quizapp.entity.UserRole;
import com.ravi.quizapp.service.IUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserRestController {
	
	@Autowired
	private IUserService userService;

	@PostMapping(value = "/save-user", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> saveUser(@RequestBody User user, @RequestBody Set<UserRole> userRoles){
		User userData = userService.saveUser(user, userRoles);
		return new ResponseEntity<>(userData, HttpStatus.CREATED);
	}
}
