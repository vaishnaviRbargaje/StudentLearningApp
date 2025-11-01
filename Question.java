package com.tka.StudentLearningApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	private String optionA,optionB,optionC,optionD;
	
	private String correctOption;
	
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;


	public Question() {
		super();
	}


	public Question(Long id, String question, String optionA, String optionB, String optionC, String optionD,
			String correctOption, Test test) {
		super();
		this.id = id;
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctOption = correctOption;
		this.test = test;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getOptionA() {
		return optionA;
	}


	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}


	public String getOptionB() {
		return optionB;
	}


	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}


	public String getOptionC() {
		return optionC;
	}


	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}


	public String getOptionD() {
		return optionD;
	}


	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}


	public String getCorrectOption() {
		return correctOption;
	}


	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", optionA=" + optionA + ", optionB=" + optionB
				+ ", optionC=" + optionC + ", optionD=" + optionD + ", correctOption=" + correctOption + ", test="
				+ test + "]";
	}


	
	
	
	
	
}
