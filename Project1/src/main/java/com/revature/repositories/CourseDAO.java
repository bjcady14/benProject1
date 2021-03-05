package com.revature.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Course;
import com.revature.models.Student;


public interface CourseDAO extends JpaRepository<Course, Integer>{
	public Optional<Student> findByName(String name);
	
	public void removeByName(String c);
	
	public Set<Course> findByBuilding(String building);
}
