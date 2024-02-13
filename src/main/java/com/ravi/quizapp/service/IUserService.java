package com.ravi.quizapp.service;

import com.ravi.quizapp.request.RegisterRequest;
import com.ravi.quizapp.response.RegisterResponse;

public interface IUserService {

	public RegisterResponse createUser(RegisterRequest request);
	
	public RegisterResponse getUser(String userName);
	
	public String deleteUser(String userName);
}
