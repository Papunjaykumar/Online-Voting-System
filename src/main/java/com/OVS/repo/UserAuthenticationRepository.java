package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.UserAuthentication;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, String>{

}
