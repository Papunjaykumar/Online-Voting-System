package com.OVS.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OVS.model.Candidate;
import com.OVS.model.User;
import com.OVS.model.Voter;
import com.OVS.service.CandidateService;
import com.OVS.service.UserService;
import com.OVS.service.VoterService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userserv;
	
	@Autowired
	private CandidateService candiserv;
	
	@Autowired
	private VoterService voterServ;
	
	//Getting the profile of the user
	@GetMapping("/profile/{id}")
	public User getProfile(@PathVariable Long id) {
		//We will getting with email or username not by  id since it is auto generated
		System.out.println(id);
		//Long modifiedId=Long.parseLong(id);
		User user= userserv.getUserById(id).get();
		//model.addAttribute("user",user);
		
		return user;
	}
	
	//Adding the  user to the database
	  @PostMapping("/addUser")
	  public User addUser(@RequestBody User user) {
		  System.out.println(user);
		  this.userserv.addUser(user);
		  return user;

	  }
	  //Deleting the user
	  @DeleteMapping("/delUser/{id}")
	  public ResponseEntity<User> deleteUser(@PathVariable Long id){
		  
		  System.out.println(id);
		  
		  User user=userserv.getUserById(id).get();
		  if(user==null) {
			  return ResponseEntity.notFound().build();
		  }
		  userserv.deleteUser(id);
		  return ResponseEntity.ok(user);
		
		  
	  }
	  
	  //Getting all the user 
	  @GetMapping("/allUser")
	  public List<User> getAllUser(){
		  return userserv.getAllUser();
	  }
	  //Getting the authorize and unauthorize user
	  @GetMapping("/authorizeUser/{flag}")
	  public List<User> getAllAuthorizeOrUnauthorizeUser(@PathVariable boolean flag){
		  return userserv.getAllUnAuthorizeUser(flag);
	  }
	  
	  //adding the candidate 
	  @PostMapping("/addCanidate")
	  public Candidate addCandidate(@RequestBody Candidate candidate) {
		  
		  
		  this.candiserv.addCandidate(candidate);
		return candidate;
		  
	  }
	  @DeleteMapping("/delCandidate/{id}")
	  public Candidate delCandidateById(@PathVariable Long id) {
		  Optional<Candidate> candidate=candiserv.getCandidateById(id);
		  System.out.println(candidate);
		  
		  if(candidate.isPresent()) {
			  candiserv.deleteCandidate(id);
			  
		  }else {
			  System.out.println("Candidate with following id is not present");
		  }
		  return candidate.get();
	  }
	  @GetMapping("getCandidate/{id}")
	  public ResponseEntity<Object> getCandidateById(@PathVariable Long id) {
		 // System.out.println("Retrieving the detail of candidate with id :"+id);
		 //Optional<Candidate> candi=userserv.getCandidateById(id);
		//return candi.get();
			/*
			 * return Optional.of(userserv.getCandidateById(id) .orElseThrow(() -> new
			 * EntityNotFoundException("No entry was found for" + " id: " + id)));
			 */
		  Candidate candi=candiserv.getCandidateById(id).orElse(null);
		  if(candi==null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO candidate with given id: "+id+" is present") ;
		  }
		  
		  	return ResponseEntity.ok().body(candi);
		  
		  }
		  
		
	  
	  
	  //Adding the user who is eligibe to vote in voter table;
	 @PostMapping("/addVoter")
	  public ResponseEntity<Object> addVoter(@RequestBody Voter voter) {
		 
		 String email= voter.getUser().getEmail();
		 
		 Voter tmp = this.voterServ.getByEmail(email);
		 if(tmp!=null) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Voter already exists");
		 }
		 
		 User user=this.userserv.getUserByEmail(email);
		 voter.setUser(user);
		 voter =  this.voterServ.addVoter(voter);
		return  ResponseEntity.ok().body(voter);
	  
	 }
	  

}
