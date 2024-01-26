package com.OVS.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.OVS.model.Candidate;
import com.OVS.service.CandidateService;
import com.OVS.service.UserService;
import com.OVS.service.VoterService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	private UserService userserv;
	@Autowired
	private VoterService voterserv;
	
	@Autowired
	private CandidateService candiserv;
	
	@ModelAttribute
	public void  addCommonData(Model model ,Principal principal) {
		
		String email = principal.getName();
		Candidate candidate=this.candiserv.getCandidateByEmail(email);
		System.out.println(candidate);
		model.addAttribute("candidate", candidate);
		
	}
	@GetMapping("/home")
	public String candidateHome() {
		
		return "/candidate/home";
	}
}
