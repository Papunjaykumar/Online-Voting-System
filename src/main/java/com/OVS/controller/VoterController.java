package com.OVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.OVS.model.ElectionCandidate;
import com.OVS.model.Vote;
import com.OVS.model.Voter;

import com.OVS.service.ElectionCandidateService;
import com.OVS.service.ElectionService;
import com.OVS.service.VoteService;


@RestController
public class VoterController {
	
	
	@Autowired
	private ElectionService electServ;
	@Autowired
	private ElectionCandidateService electcandiServ;
	
	@Autowired
	private VoteService voteserv;
	
	@PostMapping("/doVote/{electioncandidateid}")
	public ResponseEntity<Object> doVote(@RequestBody Voter voter,@PathVariable String electioncandidateid){
		
		Long id=Long.parseLong(electioncandidateid);
		
		ElectionCandidate electcandi=this.electcandiServ.getElectionCandidateById(id);
		Vote vote=this.voteserv.getByVoterAndElection(voter, electcandi.getElection());
		if(vote==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You are not eligible to vote for this election");
			
		}
		if(vote.isVoted()) {
			
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("You have already given the vote thank you!");
		}
		
		vote.setVoted(true);
		this.voteserv.updateVote(vote);
		
		electcandi.setVoteCount(electcandi.getVoteCount()+1);
		System.out.println(electcandi);
		this.electcandiServ.updateElectionCandidate(electcandi);
		
		return ResponseEntity.ok("You have successfully voted thank you!");
	}

}
