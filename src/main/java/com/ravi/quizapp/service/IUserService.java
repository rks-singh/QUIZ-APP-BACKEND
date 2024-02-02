package com.ravi.quizapp.service;

import java.util.Set;

import com.ravi.quizapp.entity.User;
import com.ravi.quizapp.entity.UserRole;

public interface IUserService {

	public User saveUser(User user, Set<UserRole> userRoles);
}
