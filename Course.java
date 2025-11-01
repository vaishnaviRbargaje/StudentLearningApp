package com.tka.StudentLearningApp.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String courseName;
	private String description;
	private double price;
	private String duration;

	private String imageName;
	private String imageType;

	@Lob
	private byte[] imageData;

	


	public Course() {
		super();
	}




	public Course(Long id, String courseName, String description, double price, String duration, String imageName,
			String imageType, byte[] imageData) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageData = imageData;
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




	public String getImageName() {
		return imageName;
	}




	public void setImageName(String imageName) {
		this.imageName = imageName;
	}




	public String getImageType() {
		return imageType;
	}




	public void setImageType(String imageType) {
		this.imageType = imageType;
	}




	public byte[] getImageData() {
		return imageData;
	}




	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}




	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", description=" + description + ", price=" + price
				+ ", duration=" + duration + ", imageName=" + imageName + ", imageType=" + imageType + ", imageData="
				+ Arrays.toString(imageData) + "]";
	}

	

}
