package com.OVS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
	Election findByName(String name);
	
}
