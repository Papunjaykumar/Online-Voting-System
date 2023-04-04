package com.OVS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Candidate;
import com.OVS.repo.CandidateRepository;

@Service
public class VotingServiceImpl implements VotingService {
	
	@Autowired
	private CandidateRepository candirepo;

	@Override
	public List<Candidate> getAllCandidate() {
		// TODO Auto-generated method stub
		return candirepo.findAll();
	}

	

	//Voter enter the id of the following candidate and if the candidate if id found then you have given the vote 
	@Override
	public void voteForCandidate(Long id) {
		// TODO Auto-generated method stub
		Optional<Candidate> candi=candirepo.findById(id);
		if(candi.isPresent()) {
			Candidate candi1=candi.get();
			candi1.setVoteCount(candi1.getVoteCount()+1);
			candirepo.save(candi1);
		}else {
			System.out.println("Candidate with id "+id+" is not present");
		}
		
	}

	

	

}
