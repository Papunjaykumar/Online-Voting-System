package com.OVS.service;



import com.OVS.model.Voter;


public interface VoterService {
	
	Voter addVoter(Voter voter) ;
	Voter getByEmail(String email);

}
