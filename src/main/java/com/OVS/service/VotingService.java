package com.OVS.service;

import java.util.List;

import com.OVS.model.Candidate;

public interface VotingService {
	public List<Candidate> getAllCandidate();
	
	public void voteForCandidate(Long id);
	

}
