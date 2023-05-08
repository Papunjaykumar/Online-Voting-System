package com.OVS.service;

import java.util.List;

import com.OVS.model.Election;

public interface ElectionService {
	public List<Election> getAllElection();
	Election getElectionByid(Long id);
	Election addElection(Election election);
	void deleteElection(Long id);
	void updateElection(Election election);
	Election getElectionByName(String name);
}
