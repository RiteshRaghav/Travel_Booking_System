package com.java.model;

public class user {
	private String UserName;
	private String Email;
	private String paswd;
	private String country;
	
	public user() {
		
	}
	public user(String UserName,String Email,String paswd,String country) {
		this.UserName=UserName;
		this.Email=Email;
		this.paswd=paswd;
		this.country=country;
		
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	} 
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPaswd() {
		return paswd;
	}
	public void setPaswd(String paswd) {
		this.paswd = paswd;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
