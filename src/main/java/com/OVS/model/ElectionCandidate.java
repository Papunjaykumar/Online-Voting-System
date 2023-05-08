package com.OVS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class ElectionCandidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="election_id",nullable=false)
	private Election election;
	@ManyToOne
	@JoinColumn(name="candidate_id",nullable=false)
	private Candidate candidate;
	private int voteCount;
	public ElectionCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ElectionCandidate( Election election, Candidate candidate, int voteCount) {
		super();
		//this.id = id;
		this.election = election;
		this.candidate = candidate;
		this.voteCount = voteCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	

}
