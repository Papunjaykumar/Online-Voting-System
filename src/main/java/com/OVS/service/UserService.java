package com.OVS.service;

import java.util.List;
import java.util.Optional;

import com.OVS.model.Candidate;
import com.OVS.model.User;

public interface UserService {
	public List<User> getAllUser();
	public Optional<User>getUserById(Long id);
	public User addUser(User user);
	public void deleteUser(Long id);
	public User getUserByUserName(String name);
	public List<User> getAllUnAuthorizeUser(boolean flag);
	public void addCandidate(Candidate candi);
	public void deleteCandidate(Long id);
	public Optional<Candidate> getCandidateById(Long id);
	public User getUserByEmail(String email);
	
	

}
