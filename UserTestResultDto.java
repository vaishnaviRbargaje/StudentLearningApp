package com.tka.StudentLearningApp.dto;

import java.util.List;

public class UserTestResultDto {
	private Long userId;
    private String username;
    private String testTitle;
    private int totalQuestion;
    private int correctQuestion;
    private int score;
    private String feedback;
    
    
    private List<QuestionResultDto> questionResults; 

    public UserTestResultDto() {}

	public UserTestResultDto(Long userId, String username, String testTitle, int totalQuestion, int correctQuestion,
			int score, String feedback, List<QuestionResultDto> questionResults) {
		super();
		this.userId = userId;
		this.username = username;
		this.testTitle = testTitle;
		this.totalQuestion = totalQuestion;
		this.correctQuestion = correctQuestion;
		this.score = score;
		this.feedback = feedback;
		this.questionResults = questionResults;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public int getCorrectQuestion() {
		return correctQuestion;
	}

	public void setCorrectQuestion(int correctQuestion) {
		this.correctQuestion = correctQuestion;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<QuestionResultDto> getQuestionResults() {
		return questionResults;
	}

	public void setQuestionResults(List<QuestionResultDto> questionResults) {
		this.questionResults = questionResults;
	}

	

	
    
    

}
