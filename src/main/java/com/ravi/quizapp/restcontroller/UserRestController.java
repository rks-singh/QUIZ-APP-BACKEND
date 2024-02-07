package com.ravi.quizapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.request.UserRequest;
import com.ravi.quizapp.response.UserResponse;
import com.ravi.quizapp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	//Creating User
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		UserResponse response = userService.createUser(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	// Getting User
	@GetMapping(value = "/{userName}", produces = "application/json")
	public ResponseEntity<UserResponse> getUser(@PathVariable String userName){
		UserResponse userRecord = userService.getUser(userName);
		return new ResponseEntity<>(userRecord, HttpStatus.OK);
	}
	
	//Deleting User.
	@DeleteMapping("/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName){
		String message = userService.deleteUser(userName);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
