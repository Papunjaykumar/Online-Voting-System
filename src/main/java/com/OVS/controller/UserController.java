package com.OVS.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OVS.model.Candidate;
import com.OVS.model.User;
import com.OVS.service.CandidateService;
import com.OVS.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userserv;
	@Autowired
	private CandidateService candiserv;
	
	
	@ModelAttribute
	public void  addCommonData(Model model ,Principal principal) {
		
		String email = principal.getName();
		User user = this.userserv.getUserByEmail(email);
		model.addAttribute("user", user);
		
	}
	
	 @GetMapping("/home")
	public String dashboard(Model model,Principal principal) {
		
		
		return "user/user_dashboard";
	}
	 
	 @GetMapping("/profile")
	 public String getProfile(Model model,Principal principal) {
		 //String email = principal.getName();
		// User user = this.userserv.getUserByEmail(email);

		 return"/user/profile";

	 }
	 
	 @GetMapping("/view_candidate")
	 public String viewCandidate(Model model,Principal principal) {
		 
		 List<Candidate> candidates = this.candiserv.getAllCandidate();
		 model.addAttribute("candidates", candidates);
		 
		 return "/user/view_candidate";
	 }
	
	/*
	 * @GetMapping("/changePassword") public String changePassword() {
	 * 
	 * return "change_password"; }
	 */
	/* @GetMapping("/viewProfile") */
	 

}
