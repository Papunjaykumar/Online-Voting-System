package com.OVS.service;

import java.util.List;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;

public interface ElectionCandidateService {
	
	void addElectionCandidate(ElectionCandidate electionCandidate);
	ElectionCandidate getElectionCandidateById(Long id);
	List<ElectionCandidate> getAllElectionCandidate();
	void updateElectionCandidate(ElectionCandidate electionCandidate);
	void deleteElectionCandidateById(Long id);
	void addAllElectionCandidate(List<ElectionCandidate> candidates);
	ElectionCandidate getElectionCandidateByElectionAndCandidate(Election election,Candidate candidate);
	
	
}
