package com.OVS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Voter",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"user_email","election"})})
		   
public class Voter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean isVoted;
	@OneToOne
	@JoinColumn(name = "user_email",referencedColumnName = "email", nullable=false)
	private User user;
	@OneToOne
	@JoinColumn(name = "election",nullable=false)
	private Election election;
	public Voter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voter(Long id, boolean isVoted, User user, Election election) {
		super();
		this.id = id;
		this.isVoted = isVoted;
		this.user = user;
		this.election = election;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isVoted() {
		return isVoted;
	}
	public void setVoted(boolean isVoted) {
		this.isVoted = isVoted;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	
	

}
