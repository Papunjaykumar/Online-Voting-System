package com.OVS.service;



import com.OVS.model.Voter;


public interface VoterService {
	
	Voter addVoter(Voter voter) ;
	Voter getByEmail(String email);
	void deleteVoterById(Long id);
	Voter findVoterById(Long id);


}
