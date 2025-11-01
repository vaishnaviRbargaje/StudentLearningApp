package com.tka.StudentLearningApp.dto;

public class EnrollmentRequest {
	
	private Long userId;
	private Long courseId;
	public EnrollmentRequest(Long userId, Long courseId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
	
	

}
