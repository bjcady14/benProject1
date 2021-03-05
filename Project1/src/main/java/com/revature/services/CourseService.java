package com.revature.services;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.models.Course;
import com.revature.repositories.CourseDAO;

@Service
public class CourseService {
	
	@Autowired
	private CourseDAO cdao;

	public Course insert(Course c) {	
		return cdao.save(c);
	}
	
	public Set<Course> findAll(){
		return cdao.findAll()
				.stream()
				.collect(Collectors.toSet());
	}
	
	public void removeByName(String c) {
		cdao.removeByName(c);
	}

	public Set<Course> findByBuilding(String building) {
		// TODO Auto-generated method stub
		return cdao.findByBuilding(building);
	}
	
}
