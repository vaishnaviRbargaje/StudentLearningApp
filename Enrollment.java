package com.tka.StudentLearningApp.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private Users user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private String status;
    
   

	private LocalDateTime timeStamp;
    

    public Enrollment() {}


	public Enrollment(Long id, Users user, Course course, String status, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.status = status;
		this.timeStamp = timeStamp;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", user=" + user + ", course=" + course + ", status=" + status + ", timeStamp="
				+ timeStamp + "]";
	}




 
}
