package com.tka.StudentLearningApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tka.StudentLearningApp.Repository.UserRepo;
import com.tka.StudentLearningApp.entity.Userprincipal;
import com.tka.StudentLearningApp.entity.Users;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Users user = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		return new Userprincipal(user);

	}

}
