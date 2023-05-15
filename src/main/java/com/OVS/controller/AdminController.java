package com.OVS.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;
import com.OVS.model.User;
import com.OVS.model.Vote;
import com.OVS.model.Voter;
import com.OVS.service.CandidateService;
import com.OVS.service.ElectionCandidateService;
import com.OVS.service.ElectionService;
import com.OVS.service.UserService;
import com.OVS.service.VoteService;
import com.OVS.service.VoterService;

@RestController

public class AdminController {
	
	@Autowired
	private UserService userserv;
	
	@Autowired
	private CandidateService candiserv;
	
	@Autowired
	private VoterService voterServ;
	
	@Autowired
	private ElectionService electServ;
	
	@Autowired
	private VoteService voteserv;
	
	@Autowired
	private ElectionCandidateService electcandiServ;
	
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
		  System.out.println(user.toString());
		  return this.userserv.addUser(user);
		  

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
	  public ResponseEntity<Object> addCandidate(@RequestBody Candidate candidate) {
		  
		  String email=candidate.getEmail();
		  Candidate temp=candiserv.getCandidateByEmail(email);
		  if(temp!=null) {
			  return ResponseEntity.status(HttpStatus.CONFLICT).body("Candidate already exists");
		  }
		  
		Candidate candiobj=  this.candiserv.addCandidate(candidate);
		return ResponseEntity.ok(candiobj);
		  
	  }
	  @DeleteMapping("/delCandidate/{id}")
	  public ResponseEntity<Object> delCandidateById(@PathVariable Long id) {
		  Candidate candidate=candiserv.getCandidateById(id).orElse(null);
		  System.out.println(candidate);

		  if(candidate==null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO candidate with given id: "+id+" is present") ;
		  }
		  candiserv.deleteCandidate(id);
		  return ResponseEntity.ok().body(candidate);

	  }		  
		  
	  @GetMapping("getCandidate/{id}")
	  public ResponseEntity<Object> getCandidateById(@PathVariable Long id) {

		  Candidate candi=candiserv.getCandidateById(id).orElse(null);
		  if(candi==null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO candidate with given id: "+id+" is present") ;
		  }

		  return ResponseEntity.ok(candi);

	  }	
	  @PostMapping("/addVoter")
	  public ResponseEntity<Object> addVoter(@RequestBody User user) {
		  User tmpuser=this.userserv.getUserByEmail(user.getEmail());
		  Voter tmp=this.voterServ.getByEmail(user.getEmail());
		  System.out.println(tmp);
		  if(tmp!=null) {
			  return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already been considered as a voter");
		  }
		  if(Period.between(tmpuser.getDateOfBirth(),LocalDate.now()).getYears()<18) {
			  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Age should be greater than 18");
		  }
		  Voter voter=new Voter();
		  voter.setUser(tmpuser);
		 voter= this.voterServ.addVoter(voter);
		  return ResponseEntity.ok(voter);
	  }
	  
	  	  
	  //Adding the user who is eligibe to vote in vote table;
		/*
		 * @PostMapping("/addVoter") public ResponseEntity<Object> addVoter(@RequestBody
		 * Voter voter) {
		 * 
		 * String email= voter.getUser().getEmail(); String
		 * election_name=voter.getElection().getName();
		 * 
		 * // Voter tmp = this.voterServ.getByEmail(email); Voter
		 * tmp=this.voterServ.getVoterByEmailAndElectionName(email, election_name);
		 * if(tmp!=null) { return
		 * ResponseEntity.status(HttpStatus.CONFLICT).body("Voter already exists"); }
		 * //getting the information from user User
		 * user=this.userserv.getUserByEmail(email); Election
		 * election=this.electServ.getElectionByName(election_name);
		 * System.out.println(election_name); voter.setUser(user);
		 * voter.setElection(election); System.out.println(election_name); voter =
		 * this.voterServ.addVoter(voter); // election.getVoters().add(voter); return
		 * ResponseEntity.ok(voter);
		 * 
		 * }
		 */
	  
	  @PostMapping("/election/{id}/addEligibleVoter") 
	  public ResponseEntity<Object> addVoter(@RequestBody List<Voter> voters,@PathVariable String id){
		  Long electionId=Long.parseLong(id);
		  Election election=this.electServ.getElectionByid(electionId);
		  System.out.println(election);
		  List<Vote> votes=new ArrayList<Vote>();
		  List<Voter>alreadyExist=new ArrayList<Voter>();
		  
		  for(Voter voter: voters) {
			  if(voteserv.getByVoterAndElection(voter, election)==null) {
				  votes.add(new Vote(false,voter,election));
			  }else {
				  alreadyExist.add(voter);
			  }
		  }
		  voteserv.addAllVote(votes);
		  return ResponseEntity.ok("Successfully added Eligible Voters");
	  }
	 
	 //adding the  election 
	 @PostMapping("/addElection")
	 public ResponseEntity<Object>addElection(@RequestBody Election election){
		 
		Election tmp= this.electServ.addElection(election);			 
		return ResponseEntity.ok(tmp);		 
	 }
	 
	 @PostMapping("/election/{id}/addCandidate")
	 public List<ElectionCandidate> addElectionCandidate(@RequestBody List<Candidate> candidates,@PathVariable String id) {
		 Long electionId=Long.parseLong(id);
		  Election election=this.electServ.getElectionByid(electionId);
		 List<ElectionCandidate>electcandidate=new ArrayList<ElectionCandidate>();
		 
		 for(Candidate candi:candidates) {
			 if(electcandiServ.getElectionCandidateByElectionAndCandidate(election, candi)==null) {
				 electcandidate.add(new ElectionCandidate(election,candi,0));
			 }
		 }
		 this.electcandiServ.addAllElectionCandidate(electcandidate);
		 return electcandidate;
	 }
	 //get all the election 
		  @GetMapping("/getAllElection")
		  public List<Election> getAllElection(){ 
			  return  this.electServ.getAllElection(); 
		  }
		 
	 //Get All voter
	 
	  

}
