package com.OVS.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String party;
	private String imageurl;
	@OneToMany(mappedBy = "candidate")
	private List<ElectionCandidate>electionCandidate;
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidate(Long id, String name, String party, String imageurl, List<ElectionCandidate> electionCandidate) {
		super();
		this.id = id;
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
	
	
	
	
	

}
