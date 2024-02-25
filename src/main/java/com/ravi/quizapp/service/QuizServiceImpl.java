package com.ravi.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.entity.exam.Quiz;
import com.ravi.quizapp.exception.QuizNotFoundException;
import com.ravi.quizapp.repo.QuizRepository;

@Service
public class QuizServiceImpl implements IQuizService {
	
	@Autowired
	private QuizRepository quizRepo;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public void deleteQuiz(Integer quizId) {
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		if(quiz.isEmpty()) {
			throw new QuizNotFoundException("Quiz is not available with Id "+quizId);
		}else {
			quizRepo.deleteById(quizId);
		}
	}

	@Override
	public List<Quiz> getQuizs() {
		 List<Quiz> quizzes = quizRepo.findAll();
		 if(quizzes.isEmpty()) {
			 throw new QuizNotFoundException("Quizzes are not available!!");
		 }else {
			 return  quizzes;
		 }
	}

	@Override
	public Quiz getQuiz(Integer quizId) {
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		if(quiz.isEmpty()) {
			throw new QuizNotFoundException("Quiz is not found with Id "+quizId);
		}else {
			return quiz.get();
		}
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		List<Quiz> quizzes = quizRepo.findByCategory(category);
		return quizzes;
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		List<Quiz> quizzes = quizRepo.findByActive(true);
		return quizzes;
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		List<Quiz> quizzes = quizRepo.findByCategoryAndActive(category,true);
		return quizzes;
	}

}
