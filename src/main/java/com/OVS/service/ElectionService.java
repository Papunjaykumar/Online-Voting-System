package com.OVS.service;

import java.util.List;

import com.OVS.model.Election;

public interface ElectionService {
	List<Election> getAllElection();
	Election getElectionByid(Long id);
	void addElection(Election election);
	void deleteElection(Long id);
	void updateElection(Election election);
}
