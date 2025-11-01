package com.tka.StudentLearningApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tka.StudentLearningApp.dto.LoginRequest;
import com.tka.StudentLearningApp.dto.PasswordUpdateRequest;
import com.tka.StudentLearningApp.entity.Users;
import com.tka.StudentLearningApp.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	

	

	// register //
	   @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public String register(@RequestBody Users user) {
		return userService.registerUser(user);
	}

	// login//
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
		Map<String, Object> userDetails = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
		return ResponseEntity.ok(userDetails);
	}

	// update password
	@PutMapping("/update-password")
	public String updatePassword(@RequestBody PasswordUpdateRequest reuqest) {
		return userService.updatePassword(reuqest);
	}

}
