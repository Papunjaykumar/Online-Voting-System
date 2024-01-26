package com.OVS.service;

import java.sql.Timestamp;
import java.util.List;

import com.OVS.model.Candidate;
import com.OVS.model.Election;
import com.OVS.model.ElectionCandidate;

public interface ElectionService {
	public List<Election> getAllElection();
	Election getElectionByid(Long id);
	Election addElection(Election election);
	void deleteElection(Long id);
	void updateElection(Election election);
	Election getElectionByName(String name);
	public boolean eletctionStatus(Timestamp startTime,Timestamp endTime);
}
