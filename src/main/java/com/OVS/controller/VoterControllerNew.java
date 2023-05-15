package com.OVS.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.OVS.model.Voter;
import com.OVS.service.ElectionCandidateService;
import com.OVS.service.ElectionService;
import com.OVS.service.VoteService;
import com.OVS.service.VoterService;

@Controller
@RequestMapping("/voter")
public class VoterControllerNew {
	
	@Autowired
	private VoterService voterserv;
	
	@Autowired
	private ElectionService electServ;
	@Autowired
	private ElectionCandidateService electcandiServ;
	
	@Autowired
	private VoteService voteserv;
	
	
	@ModelAttribute
	public void  addCommonData(Model model ,Principal principal) {
		
		String email = principal.getName();
		Voter voter = this.voterserv.getByEmail(email);
		model.addAttribute("voter", voter);
		String username=voter.getUser().getUsername();
		model.addAttribute("username",username);
		
		
	}
	
	@GetMapping("/home")
	public String voterHome() {
		
		return "/voter/voter_dashboard";
	}
	
	@GetMapping("/votePanel")
	public String votePanel() {
		
			
		
		return "/voter/vote_panel";
	}

}
