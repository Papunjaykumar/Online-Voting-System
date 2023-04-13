package com.OVS.service;

import java.util.Optional;

import com.OVS.model.Candidate;
import com.OVS.model.User;

public interface CandidateService {
	
	public void addCandidate(Candidate candi);
	public void deleteCandidate(Long id);
	public Optional<Candidate> getCandidateById(Long id);
	

}
