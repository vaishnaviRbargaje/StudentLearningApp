package com.tka.StudentLearningApp.dto;

public class EnrollmentStatusDto {
	
	private long enrolledCount;
	private long cancelledCount;
	public EnrollmentStatusDto(long enrolledCount, long cancelledCount) {
		super();
		this.enrolledCount = enrolledCount;
		this.cancelledCount = cancelledCount;
	}
	public long getEnrolledCount() {
		return enrolledCount;
	}
	public void setEnrolledCount(long enrolledCount) {
		this.enrolledCount = enrolledCount;
	}
	public long getCancelledCount() {
		return cancelledCount;
	}
	public void setCancelledCount(long cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	
	
	
	
	

}
