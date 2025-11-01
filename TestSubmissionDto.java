package com.tka.StudentLearningApp.dto;

import java.util.Map;

public class TestSubmissionDto {
	
	
	private Long testId;
	private Long userId;
	
	
	Map<Long, String> answers;


	public TestSubmissionDto(Long testId, Long userId, Map<Long, String> answers) {
		super();
		this.testId = testId;
		this.userId = userId;
		this.answers = answers;
	}


	public TestSubmissionDto() {
		super();
	}


	public Long getTestId() {
		return testId;
	}


	public void setTestId(Long testId) {
		this.testId = testId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Map<Long, String> getAnswers() {
		return answers;
	}


	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}
	
	

}
