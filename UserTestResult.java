package com.tka.StudentLearningApp.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "user_test_result")
public class UserTestResult {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int totalQuestion;
	    private int correctQuestion;
	    private int score;
	    private String feedback;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "test_id", nullable = false)
	    private Test test;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    private Users user;

	    public UserTestResult() {}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Test getTest() {
			return test;
		}

		public void setTest(Test test) {
			this.test = test;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "UserTestResult [id=" + id + ", totalQuestion=" + totalQuestion + ", correctQuestion="
					+ correctQuestion + ", score=" + score + ", feedback=" + feedback + ", test=" + test + ", user="
					+ user + "]";
		}
	    
	    
	    


  
}
