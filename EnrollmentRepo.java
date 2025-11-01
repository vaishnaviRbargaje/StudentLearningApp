package com.tka.StudentLearningApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tka.StudentLearningApp.entity.Course;
import com.tka.StudentLearningApp.entity.Enrollment;
import com.tka.StudentLearningApp.entity.Users;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long>{
	
	
	@Query("SELECT e.course FROM Enrollment e WHERE e.user.id = :userId AND e.status = 'ENROLLED'")

	   List<Course> findCoursesByUserId(@Param("userId") Long userId);	 
	   
	  List <Enrollment> findByCourse(Course course);
	  
	  
	  boolean existsByUserAndCourse(Users user,Course course);
	  
         long countByStatus(String status);


       
}
