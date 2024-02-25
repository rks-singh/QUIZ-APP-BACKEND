package com.ravi.quizapp.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.entity.exam.Question;
import com.ravi.quizapp.entity.exam.Quiz;
import com.ravi.quizapp.service.IQuestionService;
import com.ravi.quizapp.service.IQuizService;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionRestController {

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IQuizService quizService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		Question questionResponse = questionService.addQuestion(question);
		return new ResponseEntity<>(questionResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		Question questionReponse = questionService.updateQuestion(question);
		return new ResponseEntity<Question>(questionReponse, HttpStatus.OK);
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Integer questionId) {
		questionService.deleteQuestion(questionId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping(value = "/quiz/all/{quizId}", produces = "application/json")
	public ResponseEntity<List<Question>> getQuestions(@PathVariable Integer quizId) {
		Quiz quiz = new Quiz();
		quiz.setQuizId(quizId);
		List<Question> questions = questionService.getQuestionOfQuiz(quiz);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping(value = "/{questionId}", produces = "application/json")
	public ResponseEntity<Question> getQuestion(@PathVariable Integer questionId) {
		Question question = questionService.getQuestion(questionId);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	@GetMapping(value = "/quiz/{quizId}", produces = "application/json")
	public ResponseEntity<List<Question>> getQuestionByQuiz(@PathVariable Integer quizId) {
		Quiz quiz = quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> questionList = new ArrayList<>(questions);
		if (questionList.size() > quiz.getNumberOfQuestion()) {
			questionList = questionList.subList(0, quiz.getNumberOfQuestion() + 1);
		}
		Collections.shuffle(questionList);
		return new ResponseEntity<>(questionList, HttpStatus.OK);
	}

	@PostMapping(value = "/evaluate-quiz", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Integer>> evaluateQuiz(@RequestBody List<Question> questions){
		Map<String, Integer> result = questionService.evaluateQuiz(questions);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
