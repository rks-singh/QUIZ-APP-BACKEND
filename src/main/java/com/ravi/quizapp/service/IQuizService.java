package com.ravi.quizapp.service;

import java.util.List;

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.entity.exam.Quiz;


public interface IQuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public void deleteQuiz(Integer quizId);
	
	public List<Quiz> getQuizs();
	
	public Quiz getQuiz(Integer quizId);

	public List<Quiz> getQuizzesOfCategory(Category category);
	
	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzesOfCategory(Category category);

}
