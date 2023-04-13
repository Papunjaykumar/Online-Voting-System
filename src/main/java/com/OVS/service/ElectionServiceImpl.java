package com.OVS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OVS.model.Election;
import com.OVS.repo.ElectionRepository;

@Service
public class ElectionServiceImpl implements ElectionService{
	
	private ElectionRepository electRepo;

	@Override
	public List<Election> getAllElection() {
		
		return this.electRepo.findAll();
	}

	@Override
	public Election getElectionByid(Long id) {
		Election tmp=this.electRepo.findById(id).orElse(null);
	
		return tmp;
	}

	@Override
	public void addElection(Election election) {
		
		Election tmp=this.electRepo.findById(election.getId()).orElse(null);
		if(tmp==null) {
			this.electRepo.save(election);
		}else {
			System.out.println("Data with the id : "+election.getId()+" is already present");
		}
		
	}

	@Override
	public void deleteElection(Long id) {
		
		Election tmp=this.electRepo.findById(id).orElse(null);
		if(tmp==null) {
			System.out.println("NO data is prensent for the following the id: "+id);
		}else {
			this.electRepo.delete(tmp);
		}
		 
	}

	@Override
	public void updateElection(Election election) {
		
		Election tmp=this.electRepo.findById(election.getId()).orElse(null);
		if(tmp==null) {
			this.electRepo.save(election);
		}
		
	}

}
