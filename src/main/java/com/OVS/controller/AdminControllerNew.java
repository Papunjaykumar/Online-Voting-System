package com.OVS.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;
import com.OVS.model.User;
import com.OVS.model.UserAuthentication;
import com.OVS.model.UserRole;
import com.OVS.model.Vote;
import com.OVS.model.Voter;
import com.OVS.repo.UserAuthenticationRepository;
import com.OVS.service.CandidateService;
import com.OVS.service.ElectionCandidateService;
import com.OVS.service.ElectionService;
import com.OVS.service.UserService;
import com.OVS.service.VoteService;
import com.OVS.service.VoterService;

@Controller
@RequestMapping("/admin")
public class AdminControllerNew {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthenticationRepository userAuthRepo;

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
	
	//method for adding data to response
	@ModelAttribute
	public void  addCommonData(Model model ,Principal principal) {
		
		String email = principal.getName();
		User user = this.userserv.getUserByEmail(email);
		model.addAttribute("user", user);
		
	}
	
	@GetMapping("/profile")
	public String getProfile(Model model,Principal principal) {
		String email = principal.getName();
		User user = this.userserv.getUserByEmail(email);
		
		return"/admin/profile";
		
	}

	//admin panel
	
	@GetMapping("/panel")
	public String adminPanel(Model model) {

		

		return "admin/admin_dashboard";
	}
	
	
	@GetMapping("/voter")
	public String voterPanel(Model model) {

		
		return "admin/voter_panel";
	}

	@GetMapping("/election")
	public String electionPanel(Model model) {

		
		return "admin/election_panel";
	}

	@GetMapping("/addVoter/{email}")
	public String addVoter(@PathVariable("email") String email, Model model,HttpSession session) {

		

		User tmpuser = this.userserv.getUserByEmail(email);
		Voter tmp = this.voterServ.getByEmail(email);
		System.out.println(tmp);
		if (tmp != null) {
			// return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already been
			// considered as a voter");
			System.out.println("User has already been considered as a voter");
			session.setAttribute("failure","User has already been considered as a voter" );
			return "redirect:/admin/viewAllUser";
		}
		if (Period.between(tmpuser.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
			// return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Age should be
			// greater than 18");
			System.out.println("Age should be greater than 18");
			
			session.setAttribute("failure","Age should be greater than 18" );
			return "redirect:/admin/viewAllUser";
		}

		// changing the role of the user
		tmpuser.setRole(UserRole.VOTER);
		this.userserv.updateUser(tmpuser);
		userAuthRepo
				.save(new UserAuthentication(tmpuser.getEmail(), tmpuser.getPassword(), "ROLE_" + tmpuser.getRole()));
		Voter voter = new Voter();
		voter.setUser(tmpuser);
		voter = this.voterServ.addVoter(voter);
		
		session.setAttribute("success", "Voter added successfully");
		return "redirect:/admin/viewAllUser";
	}

	@GetMapping("/viewAllUser")
	public String getAllUser(Model model) {

		

		List<User> users = userserv.getAllUser();
		users = users.stream().filter(obj -> obj.getRole() == UserRole.USER).toList();

		model.addAttribute("users", users);

		return "admin/add_voter";
	}

	// View All voters
	@GetMapping("/viewallvoters")
	public String viewAllVoters(Model model) {

		

		List<User> users = userserv.getAllUser();
		users = users.stream().filter(obj -> obj.getRole() == UserRole.VOTER).toList();

		model.addAttribute("users", users);
		return "/admin/view_voters";
	}

	@GetMapping("/removevoters")
	public String removeVoter(Model model) {

		

		List<User> users = userserv.getAllUser();
		users = users.stream().filter(obj -> obj.getRole() == UserRole.VOTER).toList();

		model.addAttribute("users", users);

		return "/admin/remove_voters";
	}
	
	@GetMapping("/removevoter/{email}")
	public String removeVoter(@PathVariable("email") String email, Model model,HttpSession session ) {

		

		User tmpuser = this.userserv.getUserByEmail(email);
		Voter tmp = this.voterServ.getByEmail(email);
		
		if (tmp != null) {
			
			this.voterServ.deleteVoterById(tmp.getId());
			this.userserv.deleteUser(tmpuser.getId());
			
			this.userAuthRepo.deleteById(email);
			
			session.setAttribute("success", "Voters got deleted");
		}
		


		return "redirect:/admin/removevoters";
	}
	
	
	
	//Voter End//
	
	
	//Candidate
	
	@GetMapping("/candidate")
	public String candidatePanel(Model model) {

		

		return "admin/candidate_panel";
	}

	@GetMapping("/addCandidate")
	public String addCandidate(Model model) {

		model.addAttribute("candidate", new Candidate());

		return "admin/add_candidate";
	}
	
	//processing add candidate form
	@PostMapping("/process_addcandidate")
	public String processCandidate(@ModelAttribute("candidate")Candidate candidate,
			@RequestParam("profileImage") MultipartFile file,HttpSession session ) {
		
		try {
			//processing and uploading files
			if(file.isEmpty()) {
				System.out.println("Photo not provided");
			}else {
				//put the file to the folder and update the imageurl to the candidate
				candidate.setImageurl(file.getOriginalFilename());
				
				File saveFile=new ClassPathResource("static/image").getFile();
				
			Path path =	Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File uploaded");
			}
			//adding candidate
			this.candiserv.addCandidate(candidate);
			String email=candidate.getEmail();
			int i=email.indexOf("@");
			System.out.println(i);

			String password=email.substring(0,i)+"123";
			System.out.println(password);
			
			session.setAttribute("success", "Candidate record Entered");
			
			//adding the candidate detail in the UserAuthentication so that by using the email and password they can login
			this.userAuthRepo.save(new UserAuthentication(candidate.getEmail(),passwordEncoder.encode(password),
					"ROLE_" + candidate.getRole()));
			
			
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("failure","Something went wrong");
			return "redirect:/admin/addCandidate";
		}
		
		return "redirect:/admin/addCandidate";
	}
	
	@GetMapping("/viewallcandidate")
	public String showAllCandidate(Model model) {
		List<Candidate> candidates=this.candiserv.getAllCandidate();
		model.addAttribute("candidates", candidates);
		return "admin/view_candidates";
	}
	
	@GetMapping("/removecandidate")
	public String removeCandidates(Model model) {
		
		List<Candidate> candidates=this.candiserv.getAllCandidate();
		model.addAttribute("candidates", candidates);
		
		return "/admin/remove_candidate";
	}
	
	//removing candidate
	@GetMapping("/removecandidate/{email}")
	public String removeCcandidates(@PathVariable("email")String email,Model model,HttpSession session ) {
		Candidate candi=this.candiserv.getCandidateByEmail(email);
		Long id=candi.getId();
		this.candiserv.deleteCandidate(id);
		
		this.userAuthRepo.deleteById(email);
		
		session.setAttribute("success", "Candidate got deleted");
		
		return "redirect:/admin/removecandidate";
	}
	
	
	//Candidate ended
	//Election Start
	@GetMapping("/addelection")
	public String addElection(Model model) {
		Election election=new Election();
		model.addAttribute("election",election);
		
		return "/admin/add_election";
	}
	
	@PostMapping("/process_addelection")
	public String addElection(@ModelAttribute("election")Election election,HttpSession session) {
		
		
        
        
		this.electServ.addElection(election);
		session.setAttribute("success", "Election Added Successfully");
		
		return "redirect:/admin/addelection";
	}
	
	@GetMapping("/viewelection")
	public String viewElection(Model model) {
		List<Election>elections=this.electServ.getAllElection();
		model.addAttribute("elections",elections);
		
		return "/admin/view_election";
	}
	
	@GetMapping("/removeelection")
	public String removeElection(Model model) {
		
		List<Election>elections=this.electServ.getAllElection();
		model.addAttribute("elections",elections);
		model.addAttribute("success","Successful");
		return "/admin/remove_election";
	}
	
	@GetMapping("/removeelection/{id}")
	public String deleteElection(@PathVariable String id,HttpSession session) {
		
		Long eid=Long.parseLong(id);
		
		this.electServ.deleteElection(eid);
		session.setAttribute("success","Election deleted successfully");
		
		return "redirect:/admin/removeelection";
		
	}
	
	@GetMapping("/addelectioncandidate/{id}")
	public String addElectionCandidate(Model model,@PathVariable("id")String id) {
		
		long eid=Long.parseLong(id);
		model.addAttribute("electionid", eid);
		List<Candidate> candidates=this.candiserv.getAllCandidate();
		model.addAttribute("candidates", candidates);
		
		return "/admin/add_election_candidate";
	}
	
	@GetMapping("/addelectioncandidate/{electionId}/{candidateId}")	
	public String addElectionCandidate(@PathVariable("electionId")String electionId,@PathVariable("candidateId")String candidateId,HttpSession session) {
		
		 Long eid=Long.parseLong(electionId);
		 Long cid=Long.parseLong(candidateId);
		  Election election=this.electServ.getElectionByid(eid);
		  Candidate candidate=this.candiserv.getCandidateById(cid).get();
		 List<ElectionCandidate>electcandidate=new ArrayList<ElectionCandidate>();
		 
		 if(electcandiServ.getElectionCandidateByElectionAndCandidate(election, candidate)!=null) {
			
			 session.setAttribute("failure", "Candidate has already added for the Election");
				
				return "redirect:/admin/addelectioncandidate/"+eid;
		 }	
		 
			 
				 electcandidate.add(new ElectionCandidate(election,candidate,0));
			 
		 
		 this.electcandiServ.addAllElectionCandidate(electcandidate);
		 session.setAttribute("success", "Candidate added for the election");
		
		return "redirect:/admin/addelectioncandidate/"+eid;
	}
	
	//add voter in the particular voter
	@GetMapping("/addelectionvoter/{id}")
	public String addelectionvoter(Model model,@PathVariable("id")String id) {
		
		long eid=Long.parseLong(id);
		model.addAttribute("electionid", eid);
		
		List<User> users = userserv.getAllUser();
		users = users.stream().filter(obj -> obj.getRole() == UserRole.VOTER).toList();

		model.addAttribute("users", users);
		
		return "/admin/add_election_voter";
		
		
		
		
	}
	
	@GetMapping("/addelectionvoter/{eid}/{uid}")
	public String addElectionVoter(@PathVariable("eid")String eid,@PathVariable("uid")String uid,HttpSession session) {
		
		Long electionId=Long.parseLong(eid);
		Long userid=Long.parseLong(uid);
		
		
		Election election=this.electServ.getElectionByid(electionId);
		User user=this.userserv.getUserById(userid).get();
		
		String email=user.getEmail();
		
		Voter voter=this.voterServ.getByEmail(email);
		
		List<Vote> votes=new ArrayList<Vote>();
		
		 if(voteserv.getByVoterAndElection(voter, election)!=null) {
			 session.setAttribute("failure", "Voter has already been added");
			  
			 return "redirect:/admin/addelectionvoter/"+eid;
		  }
		 votes.add(new Vote(false,voter,election));
		 voteserv.addAllVote(votes);
		 session.setAttribute("success", "Voter added successfully in the election");
		
		return "redirect:/admin/addelectionvoter/"+eid;
	}
	
//	result
	@GetMapping("/result")
	public String resultShow(Model model,Principal principal) {
		
		List<Election> allElection = this.electServ.getAllElection();
		model.addAttribute("elections", allElection);
		
		return "/admin/result";
	}
	
	@GetMapping("/get_election_candidate/{electionId}")
	public String resultBoard(@PathVariable("electionId") Long electionId,Model model,Principal principal) {
		
		Election election = this.electServ.getElectionByid(electionId);
		boolean status = this.electServ.eletctionStatus(election.getStartTime(), election.getEndTime());
		model.addAttribute("status", status);
		List<ElectionCandidate> candidateOfElections = this.electcandiServ.getCandidateOfElections(election);
		model.addAttribute("election", election);
		model.addAttribute("electionCandidates", candidateOfElections);
		return "/admin/result_dashboard";
	}
	
	
	

}
