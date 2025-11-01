package com.tka.StudentLearningApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import com.tka.StudentLearningApp.dto.CourseRequest;
import com.tka.StudentLearningApp.entity.Course;
import com.tka.StudentLearningApp.service.CourseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/admin/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Course> addCourse(@ModelAttribute CourseRequest courseRequest) {
		try {

			Course savedCourse = courseService.addCourse(courseRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
		} catch (Exception e) {
			
				
		}
		return null;
	}

	@GetMapping("/image/{courseId}")
	public ResponseEntity<byte[]> getImageByCourseId(@PathVariable Long courseId) {
		Course course = courseService.getCourseByID(courseId);
		byte[] imageFile = course.getImageData();

		return ResponseEntity.ok().contentType(MediaType.valueOf(course.getImageType())).body(imageFile);
	}

	@GetMapping(value = "/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> viewAllCourses() {
		return ResponseEntity.ok(courseService.viewAllCourse());
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> searchByCourseName(@RequestParam String courseName) {
		return ResponseEntity.ok(courseService.searchByCourseName(courseName));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(value = "/admin/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateCourse(@PathVariable Long id, @ModelAttribute CourseRequest courseRequest) {
		try {

			Course savedCourse = courseService.updateCourse(id, courseRequest);
			return ResponseEntity.ok(savedCourse);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while updating course: " + e.getMessage());
		}
	}

}
