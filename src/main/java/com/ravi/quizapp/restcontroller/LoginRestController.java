package com.ravi.quizapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.request.LoginRequest;
import com.ravi.quizapp.response.LoginResponse;
import com.ravi.quizapp.response.RegisterResponse;
import com.ravi.quizapp.service.IUserService;
import com.ravi.quizapp.utils.JwtUtils;

@CrossOrigin
@RestController
public class LoginRestController {

	
	@Autowired
	private JwtUtils util;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private IUserService userService;
	
	// User login validation and generating token.
	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<LoginResponse> validateUser(@RequestBody LoginRequest request) {
		// Validate userName and Password from DB
		authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		String token = util.generateTocken(request.getUserName());
		RegisterResponse user = userService.getUser(request.getUserName());
		
		LoginResponse response = new LoginResponse(token,user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
