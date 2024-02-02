package com.ravi.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	

}
