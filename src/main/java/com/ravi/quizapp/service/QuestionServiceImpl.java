package com.ravi.quizapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.exam.Question;
import com.ravi.quizapp.entity.exam.Quiz;
import com.ravi.quizapp.exception.QuestionNotFoundException;
import com.ravi.quizapp.repo.QuestionRepository;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRepo.save(question);
	}

	@Override
	public void deleteQuestion(Integer questionId) {
		Optional<Question> question = questionRepo.findById(questionId);
		if (question.isEmpty()) {
			throw new QuestionNotFoundException("Question is not available with Id " + questionId);
		} else {
			questionRepo.deleteById(questionId);
		}
	}

	@Override
	public List<Question> getQuestionts() {
		List<Question> questions = questionRepo.findAll();
		if (questions.isEmpty()) {
			throw new QuestionNotFoundException("Questions are not available");
		} else {
			return questions;
		}
	}

	@Override
	public Question getQuestion(Integer questionId) {
		Optional<Question> question = questionRepo.findById(questionId);
		if (question.isEmpty()) {
			throw new QuestionNotFoundException("Question is not found with Id " + questionId);
		} else {
			return question.get();
		}
	}

	@Override
	public List<Question> getQuestionOfQuiz(Quiz quiz) {
		List<Question> questions = questionRepo.findByQuiz(quiz);
		if (questions.isEmpty()) {
			throw new QuestionNotFoundException("Questions are not available with given quiz " + quiz.getQuizTitle());
		} else {
			return questions;
		}

	}

	@Override
	public Map<String, Integer> evaluateQuiz(List<Question> questions) {
		Integer markGot = 0;
		Integer correctAnswers = 0;
		Integer attempted = 0;

		for (Question question : questions) {
			Question result = getQuestion(question.getQuestionId());
			if (result.getAnswer().equals(question.getGivenAnswer())) {
				correctAnswers++;
				int singleMark = (questions.get(0).getQuiz().getMaximumMark()) / questions.size();
				markGot += singleMark;
			}
			if (question.getGivenAnswer() != null) {
				attempted++;
			}
		}
		Map<String, Integer> result = Map.of("markGot", markGot, "correctAnswers", correctAnswers, "attempted", attempted);
		return result;
	}

}
