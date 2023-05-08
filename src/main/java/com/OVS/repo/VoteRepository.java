package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Election;
import com.OVS.model.Vote;
import com.OVS.model.Voter;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	Vote findByElectionAndVoter(Voter voter,Election election);
}
