package com.OVS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Election;
import com.OVS.model.Vote;
import com.OVS.model.Voter;
import com.OVS.repo.VoteRepository;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepo;

	@Override
	public void addAllVote(List<Vote> votes) {
		// TODO Auto-generated method stub
		this.voteRepo.saveAll(votes);
	}

	@Override
	public void updateVote(Vote vote) {
		this.voteRepo.save(vote);
		
	}

	@Override
	public void deleteVote(Vote vote) {
		// TODO Auto-generated method stub
		this.voteRepo.delete(vote);
		
	}

	@Override
	public Vote getByVoterAndElection(Voter voter, Election election) {
		// TODO Auto-generated method stub
		return this.voteRepo.findAllByElectionAndVoter(voter, election);
	}
	
	

}
