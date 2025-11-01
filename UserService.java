package com.tka.StudentLearningApp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;


import com.tka.StudentLearningApp.Repository.UserRepo;

import com.tka.StudentLearningApp.dto.PasswordUpdateRequest;

import com.tka.StudentLearningApp.entity.Users;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// user registration

	public String registerUser(Users user) {
		if(!user.getPassword().equals(user.getConfirmpassword()))
				{
			 throw new IllegalArgumentException("Password does not match");
				}
		
		  user.setRole(user.getRole().toUpperCase());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User Registered Seccessfully";
	}

	// user login
	 public Map<String, Object> login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, Object> userDetails = new HashMap<>();
            userDetails.put("id", user.getId());
            userDetails.put("username", user.getUsername());
            userDetails.put("email", user.getEmail());
            userDetails.put("role", user.getRole());

            return userDetails;
        }
	// user password update
	public String updatePassword(PasswordUpdateRequest request) {
		Users user = userRepo.findByEmail(request.getEmail())   .orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new RuntimeException("Old password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepo.save(user);
		return "Password updated Seccessfully: ";
	}
	
}
