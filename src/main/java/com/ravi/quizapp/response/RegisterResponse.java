package com.ravi.quizapp.response;

import java.util.Set;

public class RegisterResponse {
	
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String password;
	private String profile;
	private Set<String> authority;

	public RegisterResponse() {}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public Set<String> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<String> authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RegisterResponse [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", userPhone=" + userPhone + ", password=" + password + ", profile=" + profile +  "]";
	}
	
	
}
