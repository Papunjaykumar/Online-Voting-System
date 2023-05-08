package com.OVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OVS.model.Election;
import com.OVS.repo.CandidateRepository;
import com.OVS.service.ElectionService;
import com.OVS.service.VotingService;

@RestController
@RequestMapping("/vote")
public class VoterController {
	@Autowired
	private VotingService voteserv;
	
	@Autowired
	private ElectionService electServ;
	
	@PostMapping("/doVote/{id}")
	public void doVote(@PathVariable Long id) {
		voteserv.voteForCandidate(id);
		
	}
	@GetMapping("/getElection/{id}")
	public Election getElectionById(@PathVariable Long id) {
		return this.electServ.getElectionByid(id);
	}

}
