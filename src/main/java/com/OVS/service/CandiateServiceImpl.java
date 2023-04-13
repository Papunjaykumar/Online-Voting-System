package com.OVS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Candidate;
import com.OVS.repo.CandidateRepository;

@Service
public class CandiateServiceImpl implements CandidateService{
	
	@Autowired
	private CandidateRepository candirepo;

	
		@Override
		public void addCandidate(Candidate candi) {
			
			candirepo.save(candi);
			
		}

		@Override
		public void deleteCandidate(Long id) {
			
			System.out.println("Candidate with id :"+id+" is abbout to delete");
			candirepo.deleteById(id);
			System.out.println("Candidate deleted successfully");
		}
		
		@Override
		public Optional<Candidate> getCandidateById(Long id) {
			
			return candirepo.findById(id);
		}

		
	

}
