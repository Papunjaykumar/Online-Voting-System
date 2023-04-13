package com.OVS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OVS.model.Election;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

}
