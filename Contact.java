package com.tka.StudentLearningApp.entity;


import java.time.LocalDateTime;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private LocalDateTime sentAt=LocalDateTime.now();
	public Contact() {
		super();
	}
	public Contact(Long id, String name, String email, String subject, String message, LocalDateTime sentAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.sentAt = sentAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getSentAt() {
		return sentAt;
	}
	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", message="
				+ message + ", sentAt=" + sentAt + "]";
	}
	
	
	

}
