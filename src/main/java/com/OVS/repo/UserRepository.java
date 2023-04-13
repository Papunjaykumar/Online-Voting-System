package com.OVS.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByusername(String username);
	User findByEmail(String email);
	List<User> findByisAuthorize(Boolean flag);
	Optional<User> findById(Long id);
	void deleteById(Long id);

}
