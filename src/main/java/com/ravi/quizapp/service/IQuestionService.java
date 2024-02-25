package com.ravi.quizapp.service;

import java.util.List;
import java.util.Map;

import com.ravi.quizapp.entity.exam.Question;
import com.ravi.quizapp.entity.exam.Quiz;

public interface IQuestionService {
	
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public void deleteQuestion(Integer questionId);
	public List<Question> getQuestionts();
	public Question getQuestion(Integer questionId);
	public List<Question> getQuestionOfQuiz(Quiz quiz);
	public Map<String, Integer> evaluateQuiz(List<Question> questions);
}
