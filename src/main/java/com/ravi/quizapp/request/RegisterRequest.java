package com.ravi.quizapp.request;

public class RegisterRequest {

	private String userName;
	private String userEmail;
	private String userPhone;
	private String password;
	private String profile;
	
	public RegisterRequest() {}
	
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
	@Override
	public String toString() {
		return "UserRequest [userName=" + userName + ", userEmail=" + userEmail + ", userPhone=" + userPhone
				+ ", password=" + password + ", profile=" + profile + "]";
	}
	
	
	
}
