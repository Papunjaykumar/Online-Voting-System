package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
	Voter findByUser_Email(String email);
	
}
