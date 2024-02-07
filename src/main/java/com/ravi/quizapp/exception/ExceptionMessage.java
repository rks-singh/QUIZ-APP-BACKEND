package com.ravi.quizapp.exception;

import java.time.LocalDate;

public class ExceptionMessage {

	private String exceptionId;
	private String exceptioinMessage;
	private LocalDate exceptionDate;
	
	public ExceptionMessage(String exceptionId, String exceptioinMessage, LocalDate exceptionDate) {
		super();
		this.exceptionId = exceptionId;
		this.exceptioinMessage = exceptioinMessage;
		this.exceptionDate = exceptionDate;
	}
	
	public String getExceptionId() {
		return exceptionId;
	}
	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}
	public String getExceptioinMessage() {
		return exceptioinMessage;
	}
	public void setExceptioinMessage(String exceptioinMessage) {
		this.exceptioinMessage = exceptioinMessage;
	}
	public LocalDate getExceptionDate() {
		return exceptionDate;
	}
	public void setExceptionDate(LocalDate exceptionDate) {
		this.exceptionDate = exceptionDate;
	}
	
	
}
