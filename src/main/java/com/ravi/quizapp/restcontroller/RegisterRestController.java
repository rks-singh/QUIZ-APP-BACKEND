package com.ravi.quizapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.request.RegisterRequest;
import com.ravi.quizapp.response.RegisterResponse;
import com.ravi.quizapp.service.IUserService;  

@CrossOrigin
@RestController
@RequestMapping("/user")
public class RegisterRestController {

	@Autowired
	private IUserService userService;
	
	//Creating User
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<RegisterResponse> createUser(@RequestBody RegisterRequest request) {
		RegisterResponse response = userService.createUser(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	// Getting User
	@GetMapping(value = "/{userName}", produces = "application/json")
	public ResponseEntity<RegisterResponse> getUser(@PathVariable String userName){
		System.out.println("========User Name "+userName);
		RegisterResponse userRecord = userService.getUser(userName);
		System.out.println(userRecord);
		return new ResponseEntity<>(userRecord, HttpStatus.OK);
	}
	
	//Deleting User.
	@DeleteMapping("/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName){
		System.out.println("User Name => "+userName);
		String message = userService.deleteUser(userName);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
