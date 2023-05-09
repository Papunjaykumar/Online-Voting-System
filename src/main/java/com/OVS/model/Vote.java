package com.OVS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
;

@Entity
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean isVoted;
	@ManyToOne
	@JoinColumn(name="voter_id",nullable=false)
	private Voter voter;
	@ManyToOne
	@JoinColumn(name="election_id",nullable=false)
	private Election election;
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vote( boolean isVoted, Voter voter, Election election) {
		super();
		//this.id = id;
		this.isVoted = isVoted;
		this.voter = voter;
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
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	
	
	
	
}
