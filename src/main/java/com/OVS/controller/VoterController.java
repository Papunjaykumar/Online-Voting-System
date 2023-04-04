package com.OVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OVS.repo.CandidateRepository;
import com.OVS.service.VotingService;

@RestController
@RequestMapping("/vote")
public class VoterController {
	@Autowired
	private VotingService voteserv;
	
	@PostMapping("/doVote/{id}")
	public void doVote(@PathVariable Long id) {
		voteserv.voteForCandidate(id);
		
	}

}
