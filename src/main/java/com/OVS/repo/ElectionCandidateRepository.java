package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.ElectionCandidate;

@Repository
public interface ElectionCandidateRepository extends JpaRepository<ElectionCandidate, Long>{

}
