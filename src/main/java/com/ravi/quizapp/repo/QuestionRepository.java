package com.ravi.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.exam.Question;
import com.ravi.quizapp.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findByQuiz(Quiz quiz);

}
