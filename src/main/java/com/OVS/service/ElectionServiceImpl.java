package com.OVS.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;
import com.OVS.repo.ElectionRepository;

@Service
public class ElectionServiceImpl implements ElectionService{
	
	@Autowired
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
	public Election addElection(Election election) {
		
		//Election tmp=this.electRepo.findById(election.getId()).orElse(null);
		
				
		return this.electRepo.save(election);
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

	@Override
	public Election getElectionByName(String name) {
		// TODO Auto-generated method stub
		return this.electRepo.findByName(name);
	}
	@Override
	public boolean eletctionStatus(Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		System.out.println(currentTime);
		System.out.println(startTime);
		System.out.println(endTime);
		if(startTime.before(currentTime) && endTime.after(currentTime)) {
			return true;
		}
		return false;
	}
	
	

}
