package com.OVS.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Election")
public class Election implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable = false)
	private String name;
	private String description;
	@OneToMany(mappedBy = "election" )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ElectionCandidate>candidates;
	@OneToMany(mappedBy = "election")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Vote>votes;
	private LocalDate startTime;
	private LocalDate endTime;
	public Election() {
		super();  
	}
	public Election(Long id, String name, String description, List<ElectionCandidate> candidates, List<Vote> votes,
			LocalDate startTime, LocalDate endTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.candidates = candidates;
		this.votes = votes;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ElectionCandidate> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<ElectionCandidate> candidates) {
		this.candidates = candidates;
	}
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	public LocalDate getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	public LocalDate getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
}
