package com.tka.StudentLearningApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.tka.StudentLearningApp.dto.CourseRequest;
import com.tka.StudentLearningApp.entity.Course;

@Repository
public interface CourseRepo  extends JpaRepository<Course, Long>{
    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
    
    Course getCourseByid(Long CourseId);

	Course save(CourseRequest courseRequest);

	



}
