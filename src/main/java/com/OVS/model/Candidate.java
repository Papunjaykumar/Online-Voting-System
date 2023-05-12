package com.OVS.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable = false)
	private String email;
	@Column(nullable=false)
	private String name;
	@Column(nullable=true)
	private String party;
	private String imageurl;
	private UserRole role=UserRole.CANDIDATE;
	@JsonIgnore
	@OneToMany(mappedBy = "candidate" , fetch = FetchType.LAZY)
	private List<ElectionCandidate>electionCandidate;
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Candidate(Long id, String email, String name, String party, String imageurl,
			List<ElectionCandidate> electionCandidate) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.party = party;
		this.imageurl = imageurl;
		this.electionCandidate = electionCandidate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public List<ElectionCandidate> getElectionCandidate() {
		return electionCandidate;
	}
	public void setElectionCandidate(List<ElectionCandidate> electionCandidate) {
		this.electionCandidate = electionCandidate;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	
	
	
	

}
