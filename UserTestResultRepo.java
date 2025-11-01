package com.tka.StudentLearningApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.StudentLearningApp.entity.UserTestResult;
import com.tka.StudentLearningApp.entity.Users;

@Repository
public interface UserTestResultRepo extends JpaRepository<UserTestResult, Long> {
	
    List<Users> findByUserId(Long userId);
    List<UserTestResult> findByUserUsername(String username);


}
