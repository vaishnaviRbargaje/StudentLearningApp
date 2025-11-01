package com.tka.StudentLearningApp.entity;




import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Test {
	
	@Id
       @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String title;
	private String description;
	
	@OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
	private List<Question> questions=new ArrayList<Question>();
	
	@OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
	private List<UserTestResult> result=new ArrayList<UserTestResult>();

	public Test() {
		super();
	}

	public Test(Long id, String title, String description, List<Question> questions, List<UserTestResult> result) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.questions = questions;
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<UserTestResult> getResult() {
		return result;
	}

	public void setResult(List<UserTestResult> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", title=" + title + ", description=" + description + ", questions=" + questions
				+ ", result=" + result + "]";
	}



	
	
	
	
	
 

}
