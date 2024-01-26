package com.OVS.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;
import com.OVS.model.User;
import com.OVS.model.Vote;
import com.OVS.model.Voter;
import com.OVS.service.CandidateService;
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

	@Autowired
	private CandidateService candiserv;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {

		String email = principal.getName();
		Voter voter = this.voterserv.getByEmail(email);
		model.addAttribute("voter", voter);
		User user = voter.getUser();
		model.addAttribute("user", user);
		String username = voter.getUser().getUsername();
		model.addAttribute("username", username);

	}

	@GetMapping("/home")
	public String voterHome() {

		return "/voter/voter_dashboard";
	}

	@GetMapping("/votePanel")
	public String votePanel(Model model, Principal principal) {

		List<Election> elections = this.electServ.getAllElection();
		model.addAttribute("elections", elections);

		return "/voter/voter_panel";
	}

	@GetMapping("/viewProfile")
	public String showProfile(Model model, Principal principal) {

		return "/voter/profile";
	}

	@GetMapping("/view_candidate")
	public String viewCandidate(Model model, Principal principal) {

		List<Candidate> candidates = this.candiserv.getAllCandidate();
		model.addAttribute("candidates", candidates);

		return "/voter/view_candidate";
	}

	@GetMapping("/get_election_candidate/{electionId}")
	public String getElectionCandidate(@PathVariable("electionId") String electionId, Model model,
			Principal principal) {

		System.out.println(electionId);
		Election election = this.electServ.getElectionByid(Long.parseLong(electionId));
		model.addAttribute("election", election);
//		System.out.println(election);
		List<ElectionCandidate> candidateOfElections = this.electcandiServ.getCandidateOfElections(election);
		model.addAttribute("electionCandidates", candidateOfElections);
		List<Candidate> candidates = new ArrayList<>();
		for (ElectionCandidate elect : candidateOfElections) {
//			System.out.println(elect.getCandidate());
			candidates.add(elect.getCandidate());

		}

		model.addAttribute("candidates", candidates);

		return "/voter/election_candidate";
	}

//	givning the vote
	@GetMapping("/giveVote/{electionCandidateId}/{voterId}")
	public String giveVote(@PathVariable("electionCandidateId") String electionCandidateId,
			@PathVariable("voterId") String voterId, Model model, HttpSession session) {

		Long id = Long.parseLong(electionCandidateId);
		Long vid = Long.parseLong(voterId);

		ElectionCandidate electcandi = this.electcandiServ.getElectionCandidateById(id);
		Voter voter = this.voterserv.findVoterById(vid);
		Election election = electcandi.getElection();
		boolean status = this.electServ.eletctionStatus(election.getStartTime(), election.getEndTime());
		System.out.println(status);
		if (status) {
			Vote vote = this.voteserv.getByVoterAndElection(voter, election);
			if (vote == null) {
				session.setAttribute("failure", "You are not eligible to vote for this election");
				return "redirect:/voter/get_election_candidate/"+election.getId();

			}
			if (vote.isVoted()) {
				session.setAttribute("failure", "You have already given the vote thank you!");
				return "redirect:/voter/get_election_candidate/"+election.getId();
			}

			vote.setVoted(true);
			this.voteserv.updateVote(vote);

			electcandi.setVoteCount(electcandi.getVoteCount() + 1);
			System.out.println(electcandi);
			this.electcandiServ.updateElectionCandidate(electcandi);
			session.setAttribute("success", "You have successfully voted thank you!");
		} else {
			session.setAttribute("failure", "Election has either not started or has been ended Thank you !!");
		}
		return "redirect:/voter/get_election_candidate/"+election.getId();
	}

}
