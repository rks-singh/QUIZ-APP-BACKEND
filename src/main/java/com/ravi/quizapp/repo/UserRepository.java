package com.ravi.quizapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUserEmail(String userEmail);
	
	public Optional<User> findByUserName(String userName);
	
}
