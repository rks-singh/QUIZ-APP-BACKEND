package com.ravi.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
