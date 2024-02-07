package com.ravi.quizapp.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ravi.quizapp.exception.ExceptionMessage;
import com.ravi.quizapp.exception.UserAlreadyExistException;
import com.ravi.quizapp.exception.UserNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = UserAlreadyExistException.class)
	public ResponseEntity<ExceptionMessage> handleUserAlreadyExistException(UserAlreadyExistException ex){
		ExceptionMessage msg = new ExceptionMessage("EX-101", ex.getMessage(), LocalDate.now());
		return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleUserNotFoundException(UserNotFoundException ex){
		ExceptionMessage msg = new ExceptionMessage("EX-102", ex.getMessage(), LocalDate.now());
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
