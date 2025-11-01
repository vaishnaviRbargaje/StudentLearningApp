package com.tka.StudentLearningApp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tka.StudentLearningApp.dto.TestDto;
import com.tka.StudentLearningApp.dto.TestSubmissionDto;
import com.tka.StudentLearningApp.dto.UserTestResultDto;

import com.tka.StudentLearningApp.service.TestService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserTestController {
	
	
	
	@Autowired
	private TestService service;
	
	
	@PostMapping("/test/submit")
	public ResponseEntity<UserTestResultDto> submitTest(@RequestBody TestSubmissionDto testDto)
	{
		 UserTestResultDto resultDto = service.submitTest(testDto);
        return ResponseEntity.ok(resultDto);
	}
	
	
	@GetMapping("/test/getTest")
	public ResponseEntity<List<TestDto>> getAllTest()
	{
		
		return ResponseEntity.ok(service.getAllTest());
		
	}
	
	 @GetMapping("/results/{username}")
	    public List<UserTestResultDto> getResultsByUsername(@PathVariable String username) {
	        return service.getResultByUserName(username);
	    }
	
	

	
	
	
	
	
	
	
	

	
	
	
	
}
