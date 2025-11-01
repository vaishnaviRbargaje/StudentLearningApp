package com.tka.StudentLearningApp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tka.StudentLearningApp.Repository.CourseRepo;

import com.tka.StudentLearningApp.dto.CourseRequest;
import com.tka.StudentLearningApp.entity.Course;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepository;

	// add course
	@Transactional
	public Course addCourse(CourseRequest courseRequest) throws IOException {
		  Course course = new Course();
	        if (courseRequest.getId() != null && courseRepository.existsById(courseRequest.getId())) {
	            throw new RuntimeException("Course with ID " + courseRequest.getId() + " already exists");
	        }



	        course.setCourseName(courseRequest.getCourseName());
	        course.setDescription(courseRequest.getDescription());
	        course.setPrice(courseRequest.getPrice());
	        course.setDuration(courseRequest.getDuration());

	        if (courseRequest.getImageFile() != null && !courseRequest.getImageFile().isEmpty()) {
	            course.setImageName(courseRequest.getImageFile().getOriginalFilename());
	            course.setImageType(courseRequest.getImageFile().getContentType());
	            course.setImageData(courseRequest.getImageFile().getBytes());
	        }

	    
	        return courseRepository.save(course);

	}

	/// view All course
	@Transactional
	public List<Course> viewAllCourse() {
		return courseRepository.findAll();
	}

	// update the course
	@Transactional
	public Course updateCourse(Long id, CourseRequest courseRequest) throws IOException {

		Course existing = courseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Course Not Found With id:" + id));

		existing.setCourseName(courseRequest.getCourseName());
		existing.setDescription(courseRequest.getDescription());
		existing.setPrice(courseRequest.getPrice());
		existing.setDuration(courseRequest.getDuration());

	        if (courseRequest.getImageFile() != null && !courseRequest.getImageFile().isEmpty()) {
	        	existing.setImageName(courseRequest.getImageFile().getOriginalFilename());
	            existing.setImageType(courseRequest.getImageFile().getContentType());
	            existing.setImageData(courseRequest.getImageFile().getBytes());
	        }


		return courseRepository.save(existing);
	}

	

	// search by course name
	@Transactional
	public List<Course> searchByCourseName(String courseName) {
		return courseRepository.findByCourseNameContainingIgnoreCase(courseName);
	}
     
	@Transactional
	public Course getCourseByID(Long courseId) {
	
		return courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Not found with id"+courseId));
	}

	

}
