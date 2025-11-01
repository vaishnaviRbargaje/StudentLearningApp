package com.tka.StudentLearningApp.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;

@Entity
@Table(name = "users") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    private String email;
    private String password;
    
    @Transient
    private String confirmpassword;
    private String role="USERS";
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<UserTestResult> result=new ArrayList<UserTestResult>();

    public Users() {}

	public Users(Long id, String username, String email, String password, String confirmpassword, String role,
			List<UserTestResult> result) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.role = role;
		this.result = result;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserTestResult> getResult() {
		return result;
	}

	public void setResult(List<UserTestResult> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", confirmpassword=" + confirmpassword + ", role=" + role + ", result=" + result + "]";
	}


    

	

	}


  


