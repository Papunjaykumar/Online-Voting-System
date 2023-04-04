package com.OVS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OVS.model.Candidate;
import com.OVS.model.User;
import com.OVS.repo.CandidateRepository;
import com.OVS.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CandidateRepository candirepo;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		System.out.println("Get all user");
		System.out.println(userRepo.findAll());
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findById(id)).get();
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("user addedd");
		User userobj=this.userRepo.save(user);
		System.out.println(userobj);
		return userobj;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		System.out.println("User with id :"+id +" is about to delete");
		userRepo.deleteById(id);
		System.out.println("User of id "+id+" got deleted");
	}

	@Override
	public User getUserByUserName(String name) {
		// TODO Auto-generated method stub
		return userRepo.findByusername(name);
	}

	@Override
	public List<User> getAllUnAuthorizeUser(boolean flag) {
		// TODO Auto-generated method stub
		return userRepo.findByisAuthorize(flag);
	}
	//Adding the candidate this is done only by the admin
	@Override
	public void addCandidate(Candidate candi) {
		// TODO Auto-generated method stub
		candirepo.save(candi);
		
	}

	@Override
	public void deleteCandidate(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Candidate with id :"+id+" is abbout to delete");
		candirepo.deleteById(id);
		System.out.println("Candidate deleted successfully");
	}
	
	@Override
	public Optional<Candidate> getCandidateById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(candirepo.findById(id).get());
	}
	

}
