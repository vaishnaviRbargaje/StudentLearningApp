package com.tka.StudentLearningApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.StudentLearningApp.Repository.ContactRepo;
import com.tka.StudentLearningApp.entity.Contact;


@Service
public class ContactService {
	
	
	@Autowired
	private ContactRepo contactRepo;
	
	
	public Contact saveMessage(Contact cont)
	{
		Contact con=contactRepo.save(cont);
		
		return con;
		
	}
	
	
	public List<Contact> getAllMessage()
	{
		return contactRepo.findAll();
	}

}
