package com.OVS.service;

import java.util.List;
import java.util.Optional;

import com.OVS.model.Candidate;
import com.OVS.model.User;

public interface CandidateService {
	
	public Candidate addCandidate(Candidate candi);
	public void deleteCandidate(Long id);
	public Optional<Candidate> getCandidateById(Long id);
	public Candidate getCandidateByEmail(String email);
	public List<Candidate> getAllCandidate();
	

}
