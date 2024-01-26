package com.OVS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;

@Repository
public interface ElectionCandidateRepository extends JpaRepository<ElectionCandidate, Long>{
	
	ElectionCandidate findByElectionAndCandidate(Election election,Candidate candidate);
	List<ElectionCandidate> findByElection(Election election);
}
