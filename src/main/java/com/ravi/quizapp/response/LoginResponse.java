package com.ravi.quizapp.response;

public class LoginResponse {

	private String token;
	private RegisterResponse response;
	
	public LoginResponse() {}

	public LoginResponse(String token, RegisterResponse response) {
		this.token = token;
		this.response = response;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public RegisterResponse getResponse() {
		return response;
	}

	public void setResponse(RegisterResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", response=" + response + "]";
	}
	
	
	

	
}
