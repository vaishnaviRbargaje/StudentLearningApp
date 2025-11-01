package com.tka.StudentLearningApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.StudentLearningApp.entity.Contact;
import com.tka.StudentLearningApp.service.ContactService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/contact")
public class ContactController {
	
	
	
	@Autowired
	private ContactService service;
	
	
	@PostMapping("/user/post")
	public Contact saveMessage(@RequestBody Contact cont)
	{
		
	       return service.saveMessage(cont);
		
	}
	
	@GetMapping("/admin/get")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Contact> getAllMessage()
	{
		return service.getAllMessage();
	}

}
