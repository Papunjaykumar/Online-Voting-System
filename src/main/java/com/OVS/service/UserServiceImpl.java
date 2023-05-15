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
	
	@Override
	public User getUserByEmail(String email) {
		
		return this.userRepo.findByEmail(email);
	}

	@Override
	public User updateUser(User user) {
		return this.userRepo.save(user);
		
	}
	

}
