package com.OVS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Voter;
import com.OVS.repo.VoterRepository;

@Service
public class VoterServiceImpl implements VoterService{
	
	@Autowired
	private VoterRepository voterRepo;

	@Override
	public Voter addVoter(Voter voter) {
		// TODO Auto-generated method stub
		System.out.println("Voter is getting added to the database please wait.....");
		Voter voterobj=this.voterRepo.save(voter);
		return voterobj;
		
	}

	@Override
	public Voter getByEmail(String email) {
		// TODO Auto-generated method stub
		return this.voterRepo.findByUser_Email(email);
	}

	@Override
	public void deleteVoterById(Long id) {
		
		Voter voter=this.voterRepo.findById(id).orElse(null);
		if(voter==null) {
			System.out.println("Voter has not been added");
		}else {
			this.voterRepo.delete(voter);
			System.out.println("Voter deleted successfully");
		}
	}

	@Override
	public Voter findVoterById(Long id) {
		Voter voter=this.voterRepo.findById(id).orElse(null);
		if(voter==null) {
			System.out.println("Voter not founded");
			return voter;
		}
		System.out.println("Voter founded");
		return voter;
	}

}
