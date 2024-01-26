package com.OVS.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Election")
public class Election implements Serializable{
	
	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable = false)
	private String name;
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "election",fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private List<ElectionCandidate>candidates;
	@JsonIgnore
	@OneToMany(mappedBy = "election", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Vote>votes;
	private Timestamp startTime;
	private Timestamp endTime;
	public Election() {
		super();  
	}
	public Election(Long id, String name, String description, List<ElectionCandidate> candidates, List<Vote> votes,
			String startTime, String endTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.candidates = candidates;
		this.votes = votes;
		this.startTime = Timestamp.valueOf(startTime);
		this.endTime = Timestamp.valueOf(endTime);
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
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		 LocalDateTime sTime = LocalDateTime.parse(startTime, inputFormatter);
		 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String startDateTime = sTime.format(outputFormatter);

		this.startTime = Timestamp.valueOf(startDateTime);
		
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		 LocalDateTime eTime = LocalDateTime.parse(endTime, inputFormatter);
		 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String endDateTime = eTime.format(outputFormatter);

		
		this.endTime = Timestamp.valueOf(endDateTime);
	}
	@Override
	public String toString() {
		return "Election [id=" + id + ", name=" + name + ", description=" + description + ", candidates=" + candidates
				+ ", votes=" + votes + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
	
	
	
}
