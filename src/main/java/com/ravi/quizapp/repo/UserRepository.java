package com.ravi.quizapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserEmail(String userEmail);
	
	Optional<User> findByUserName(String userName);
}
