package com.tka.StudentLearningApp.dto;

public class EnrollmentDto {
	
	  private Long id;
	    private String username;
	    private String courseName;
	    private String status;
		public EnrollmentDto() {
			super();
		}
		public EnrollmentDto(Long id, String username, String courseName, String status) {
			super();
			this.id = id;
			this.username = username;
			this.courseName = courseName;
			this.status = status;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
	    
	    

}
