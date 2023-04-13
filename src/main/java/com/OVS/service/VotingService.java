package com.OVS.service;

import java.util.List;

import com.OVS.model.Candidate;
import com.OVS.model.Voter;

public interface VotingService {
	public List<Candidate> getAllCandidate();
	public void voteForCandidate(Long id);
	

}
