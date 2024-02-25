package com.ravi.quizapp.restcontroller;

import java.util.List;

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

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.entity.exam.Quiz;
import com.ravi.quizapp.service.IQuizService;

@CrossOrigin
@RestController
@RequestMapping("/quiz")
public class QuizRestController {

	@Autowired
	private IQuizService quizService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		Quiz quizResponse = quizService.addQuiz(quiz);
		return new ResponseEntity<>(quizResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
		Quiz quizResponse = quizService.updateQuiz(quiz);
		return new ResponseEntity<Quiz>(quizResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{quizId}")
	public ResponseEntity<Void> deleteQuiz(@PathVariable Integer quizId) {
		quizService.deleteQuiz(quizId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Quiz>> getQuizzes() {
		List<Quiz> quizzes = quizService.getQuizs();
		return new ResponseEntity<>(quizzes, HttpStatus.OK);
	}

	@GetMapping(value = "/{quizId}", produces = "application/json")
	public ResponseEntity<Quiz> getQuiz(@PathVariable Integer quizId) {
		Quiz quiz = quizService.getQuiz(quizId);
		return new ResponseEntity<Quiz>(quiz, HttpStatus.OK);
	}
	
	@GetMapping(value = "/category/{categoryId}", produces = "application/json")
	public ResponseEntity<List<Quiz>>getQuizzesOfCategory(@PathVariable Integer categoryId){
		Category category = new Category();
		category.setCategoryId(categoryId);
		List<Quiz> quizzes = quizService.getQuizzesOfCategory(category);
		return new ResponseEntity<>(quizzes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/active", produces = "application/json")
	public ResponseEntity<List<Quiz>> getActiveQuizzes(){
		List<Quiz> quizzes = quizService.getActiveQuizzes();
		return new ResponseEntity<>(quizzes,HttpStatus.OK);
	}
	
	@GetMapping(value = "/category/active/{categoryId}", produces = "application/json")
	public ResponseEntity<List<Quiz>>getActiveQuizzesOfCategory(@PathVariable Integer categoryId){
		Category category = new Category();
		category.setCategoryId(categoryId);
		List<Quiz> quizzes = quizService.getActiveQuizzesOfCategory(category);
		return new ResponseEntity<>(quizzes,HttpStatus.OK);
	}
}
