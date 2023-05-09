package com.OVS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;
import com.OVS.repo.ElectionCandidateRepository;

@Service
public class ElectionCandidateServiceImpl implements ElectionCandidateService {
	
	@Autowired
	private ElectionCandidateRepository electRepo;
	
	@Override
	public void addElectionCandidate(ElectionCandidate electionCandidate) {
		
		ElectionCandidate temp=this.electRepo.findById(electionCandidate.getId()).orElse(null);
		if(temp==null) {
			//Saving the ElctionCAndidate to the database
			this.electRepo.save(electionCandidate);
			System.out.println("Candidate added");
		}
		
		
	}
	

	@Override
	public ElectionCandidate getElectionCandidateById(Long id) {
		
		return this.electRepo.findById(id).orElse(null);
	}

	@Override
	public List<ElectionCandidate> getAllElectionCandidate() {
		
		return this.electRepo.findAll();
	}

	@Override
	public void updateElectionCandidate(ElectionCandidate electionCandidate) {
		
		ElectionCandidate tmp=this.electRepo.findById(electionCandidate.getId()).orElse(null);
		if(tmp==null) {
			System.out.println("Data not found in the database with the follwing id"+electionCandidate.getId());
		}else {
			this.electRepo.save(electionCandidate);
		}
		
	}

	@Override
	public void deleteElectionCandidateById(Long id) {
		
		ElectionCandidate temp=this.electRepo.findById(id).orElse(null);
		if(temp==null) {
			System.out.println("ElectionCandidate Not Found");
			
		}else {
			this.electRepo.delete(temp);
		}
		
	}

	@Override
	public void addAllElectionCandidate(List<ElectionCandidate> candidates) {
		// TODO Auto-generated method stub
		
		this.electRepo.saveAll(candidates);
		
	}

	@Override
	public ElectionCandidate getElectionCandidateByElectionAndCandidate(Election election, Candidate candidate) {
		// TODO Auto-generated method stub
		return this.electRepo.findByElectionAndCandidate(election, candidate);
	}

}
