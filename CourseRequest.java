package com.tka.StudentLearningApp.dto;

import org.springframework.web.multipart.MultipartFile;

public class CourseRequest {

	private Long id;
	private String courseName;
	private String description;
	private double price;
	private String duration;
	private MultipartFile imageFile;
	


	public CourseRequest() {
		super();
	}



	public CourseRequest(Long id, String courseName, String description, double price, String duration,
			MultipartFile imageFile) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.imageFile = imageFile;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getDuration() {
		return duration;
	}



	public void setDuration(String duration) {
		this.duration = duration;
	}



	public MultipartFile getImageFile() {
		return imageFile;
	}



	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	
	
}
