package com.example.seperate_db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.seperate_db.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

	@Query(value = "SELECT * FROM user_details WHERE username = ?1", nativeQuery = true)
	Optional<UserDetails> findByUsername(String username);
}
