package com.OVS.model;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usertable")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable = false)
	@NotBlank(message="Username field is required")
	@Size(min=2,max=20,message="min 2 and max 20 character are allowed !!")
	private String username;
	@Column(nullable = false)
	private String password;
	
	@Column(unique=true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private boolean isAdmin;
	private boolean isAuthorize;
	private UserRole role=UserRole.USER;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, boolean isAdmin, boolean isAuthorize) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth ;
		System.out.println(isAdmin+" "+isAuthorize);
		this.isAdmin = isAdmin;
		this.isAuthorize = isAuthorize;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		System.out.println(isAdmin);
		this.isAdmin = isAdmin;
	}
	public boolean isAuthorize() {
		return isAuthorize;
	}
	public void setAuthorize(boolean isAuthorize) {
		this.isAuthorize = isAuthorize;
	}	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", isAdmin="
				+ isAdmin + ", isAuthorize=" + isAuthorize + ", role=" + role + "]";
	}
	
	
	
	
}
