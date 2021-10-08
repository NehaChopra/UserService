package com.homestars.UserService.repository;

import com.homestars.UserService.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 
 * @author nchopra
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
}