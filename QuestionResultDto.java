package com.tka.StudentLearningApp.dto;

public class QuestionResultDto {
	
	
	  private Long questionId;
	    private String question;
	    private String selectedAnswer;
	    private String correctAnswer;
	    private boolean isCorrect;
		public QuestionResultDto() {
			super();
		}
		public QuestionResultDto(Long questionId, String question, String selectedAnswer, String correctAnswer,
				boolean isCorrect) {
			super();
			this.questionId = questionId;
			this.question = question;
			this.selectedAnswer = selectedAnswer;
			this.correctAnswer = correctAnswer;
			this.isCorrect = isCorrect;
		}
		public Long getQuestionId() {
			return questionId;
		}
		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		public String getSelectedAnswer() {
			return selectedAnswer;
		}
		public void setSelectedAnswer(String selectedAnswer) {
			this.selectedAnswer = selectedAnswer;
		}
		public String getCorrectAnswer() {
			return correctAnswer;
		}
		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}
		public boolean isCorrect() {
			return isCorrect;
		}
		public void setCorrect(boolean isCorrect) {
			this.isCorrect = isCorrect;
		}
	
	    
	
	
	

}
