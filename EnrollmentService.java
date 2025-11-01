package com.tka.StudentLearningApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.tka.StudentLearningApp.Repository.CourseRepo;
import com.tka.StudentLearningApp.Repository.EnrollmentRepo;
import com.tka.StudentLearningApp.Repository.UserRepo;
import com.tka.StudentLearningApp.dto.EnrollmentDto;
import com.tka.StudentLearningApp.dto.EnrollmentRequest;
import com.tka.StudentLearningApp.dto.EnrollmentStatusDto;
import com.tka.StudentLearningApp.entity.Course;
import com.tka.StudentLearningApp.entity.Enrollment;
import com.tka.StudentLearningApp.entity.Users;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepo enrollmentRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CourseRepo courseRepo;

	// enroll a user in course // student// learner
	public Enrollment enrollUser(EnrollmentRequest request) {
	    Users user = userRepo.findById(request.getUserId())
	        .orElseThrow(() -> new RuntimeException("User not found"));


	    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	        throw new RuntimeException("Admins cannot enroll in courses.");
	    }

	    Course course = courseRepo.findById(request.getCourseId())
	        .orElseThrow(() -> new RuntimeException("Course not found"));

	 
	    if (enrollmentRepo.existsByUserAndCourse(user, course)) {
	        throw new RuntimeException("User is already enrolled in this course");
	    }

	    Enrollment enrollment = new Enrollment();
	    enrollment.setUser(user);
	    enrollment.setCourse(course);
	    enrollment.setStatus("Enrolled");
	    enrollment.setTimeStamp(LocalDateTime.now());

	    return enrollmentRepo.save(enrollment);
	}


	// get enrollment by user//

	public List<Course> getUserEnrollments(Long userId) {
		Users user=userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found "));
		return enrollmentRepo.findCoursesByUserId(userId);

	}

	// get enrollment by course//
	public List<EnrollmentDto> getCourseEnrollments(Long courseId) {
		   Course course = courseRepo.findById(courseId)
			        .orElseThrow(() -> new RuntimeException("Course not found"));

			    List<Enrollment> enrollments = enrollmentRepo.findByCourse(course);

			    return enrollments.stream()
			        .map(e -> new EnrollmentDto(
			            e.getId(),
			            e.getUser() != null ? e.getUser().getUsername() : "N/A",
			            e.getCourse() != null ? e.getCourse().getCourseName() : "N/A",
			            e.getStatus()
			        ))
			        .collect(Collectors.toList());
	}
	
	// for stats
	
	public EnrollmentStatusDto enrollstats()
	{
		
		long enrolledCount =enrollmentRepo.countByStatus("Enrolled");
		long cancelledCount=enrollmentRepo.countByStatus("Cancelled");
		
		return new  EnrollmentStatusDto(enrolledCount, cancelledCount);
		
		
	}
	
	// admin can view enrollment//
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<EnrollmentDto> getAllEnrollments()
	{
	    List<Enrollment> enrollments = enrollmentRepo.findAll();

	    return enrollments.stream()
	            .map(e -> new EnrollmentDto(
	                    e.getId(),
	                    e.getUser() != null ? e.getUser().getUsername() : "N/A",
	                    e.getCourse() != null ? e.getCourse().getCourseName() : "N/A",
	                    e.getStatus()
	            ))
	            .collect(Collectors.toList());

	}
	
	// admin can cancel enrollment //
	
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN')")
	public void cancelEnrollment(Long Id)
	{
		Enrollment enrollment=enrollmentRepo.findById(Id).orElseThrow(()->new RuntimeException("Enrollment not found"));
		
		enrollment.setStatus("Cancelled");
		enrollmentRepo.save(enrollment);
		
		
	}


	
	

}
