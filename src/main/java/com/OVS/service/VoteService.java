package com.OVS.service;

import java.util.List;

import com.OVS.model.Election;
import com.OVS.model.Vote;
import com.OVS.model.Voter;

public interface VoteService {
	
	void addAllVote(List<Vote> votes);
	void updateVote(Vote vote);
	void deleteVote(Vote vote);
	Vote getByVoterAndElection(Voter voter,Election election);

}
