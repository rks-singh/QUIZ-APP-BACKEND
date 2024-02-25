package com.ravi.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

	public List<Quiz> findByCategory(Category category);

	public List<Quiz> findByActive(Boolean status);

	public List<Quiz> findByCategoryAndActive(Category category, boolean b);

}
