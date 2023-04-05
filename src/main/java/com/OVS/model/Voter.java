package com.OVS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Voter")
public class Voter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//this will be mapping to the user email id
	@OneToOne
	private User user;
	public Voter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voter(Long id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
