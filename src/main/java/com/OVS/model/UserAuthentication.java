package com.OVS.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAuthentication {
	@Id
	private String email;
	private String password;
	private String role;
	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserAuthentication [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
	

}
