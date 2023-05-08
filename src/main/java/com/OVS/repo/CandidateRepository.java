package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	Candidate findByEmail(String email);

}
