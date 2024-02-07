package com.ravi.quizapp.service;

import com.ravi.quizapp.request.UserRequest;
import com.ravi.quizapp.response.UserResponse;

public interface IUserService {

	public UserResponse createUser(UserRequest request);
	
	public UserResponse getUser(String userName);
	
	public String deleteUser(String userName);
}
