package com.OVS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.OVS.model.CustomeUserDetails;
import com.OVS.model.UserAuthentication;
import com.OVS.repo.UserAuthenticationRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserAuthenticationRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserAuthentication>user=userrepo.findById(email);
		user.orElseThrow(()->
		new UsernameNotFoundException("User not found :: "+email)
				);
		return new CustomeUserDetails(user.get());
				
	}

}
