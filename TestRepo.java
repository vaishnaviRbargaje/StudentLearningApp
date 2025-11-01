package com.tka.StudentLearningApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.StudentLearningApp.entity.Test;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
	
	

}
