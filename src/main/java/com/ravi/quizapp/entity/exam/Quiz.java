package com.ravi.quizapp.entity.exam;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer quizId;
	
	private String quizTitle;
	private String quizDescription;
	private Integer maximumMark;
	private Integer numberOfQuestion;
	private Boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
    @JsonIgnore
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Question> questions;
	
	public Quiz() {}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public Integer getMaximumMark() {
		return maximumMark;
	}

	public void setMaximumMark(Integer maximumMark) {
		this.maximumMark = maximumMark;
	}

	public Integer getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(Integer numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizTitle=" + quizTitle + ", quizDescription=" + quizDescription
				+ ", maximumMark=" + maximumMark + ", numberOfQuestion=" + numberOfQuestion + ", active=" + active
				+ ", category=" + category + ", questions=" + questions + "]";
	}
	
	
}
