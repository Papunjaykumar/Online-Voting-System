package com.OVS.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;




@Entity
@Table(name="Voter",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"user_email"})})
		   
public class Voter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_email",referencedColumnName = "email", nullable=false)
	private User user;
	@OneToMany(mappedBy = "voter",fetch=FetchType.EAGER)
	private List<Vote>votes;
	public Voter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voter(Long id, User user, List<Vote> votes) {
		super();
		this.id = id;
		this.user = user;
		this.votes = votes;
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
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	
	
	
	
	
	

}
