package com.tka.StudentLearningApp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.StudentLearningApp.Repository.UserTestResultRepo;
import com.tka.StudentLearningApp.dto.TestDto;
import com.tka.StudentLearningApp.dto.UserTestResultDto;
import com.tka.StudentLearningApp.entity.Test;
import com.tka.StudentLearningApp.entity.UserTestResult;
import com.tka.StudentLearningApp.service.TestService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTestController {
	
	
	
	
	@Autowired
	private TestService service;
	
	
	@Autowired
	private UserTestResultRepo userTestResultRepo;
	
	
	
	
	
	  @PostMapping("/test/add")
	  public ResponseEntity<Test> addTest(@RequestBody TestDto testDto)
	  {
		  return ResponseEntity.ok(service.addTest(testDto));
	  }
	  
	  
	  @DeleteMapping("/test/delete/{id}")
	  public ResponseEntity<String> deleteTest(@PathVariable Long id)
	  {
		 service.deleteTest(id);
		 
		 return ResponseEntity.ok("Test Deleted Successful!");
	  }
	  
	  
	
	  
	  @GetMapping("test/getResult")
	  public ResponseEntity<List<UserTestResultDto>>  getAllResult()
	  {
		  return ResponseEntity.ok(service.getAllResults());
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  

	
	
	

}
