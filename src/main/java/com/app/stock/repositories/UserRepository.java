package com.app.stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.stock.entities.User;
//Repository interface for User entity, extending JpaRepository.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * Retrieve a user by their email address.
	 * Return Optional containing the user if found, empty otherwise.
	 */
    
	Optional<User> findByUserEmail(String email);
	
	/*
	 * Check if a user with the given email address exists.
	 * Return True if a user with the given email address exists, false otherwise.
	 */
	boolean existsByUserEmail(String email);

}
