package com.revature.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.StudentNotFoundException;
import com.revature.models.Student;
import com.revature.repositories.StudentDAO;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	public Set<Student> findAll(){
		return studentDAO.findAll()
				.stream()
				.collect(Collectors.toSet());
	}
	
	
	public Student findByName(String name) {
		return studentDAO.findByName(name)
				.orElseThrow( () -> new StudentNotFoundException("No student with name " +name) );
	}
	
	public Student insert(Student s) {
		return studentDAO.save(s);
	}
	
	public void removeByName(String s) {
		studentDAO.removeByName(s);
	}
	
	public void updateAcademicStatus() {
		studentDAO.updateAcademicStatus();
	}
	
	public String findAvgGpa(String cat) {
		Optional<Double> av;
		if(cat.equals("all")) {
			av = studentDAO.findAvgGpa();
		} else {
			av = studentDAO.findAvgGpaSome();
		}
		if(av.isPresent()) {
			String s=String.valueOf(av.get());
			return s;
		} else {
			return "-1";
		}
	
	}
	
	public void removeAll() {
		studentDAO.deleteAll();
	}
	
//	public void expel(){studentDAO.expell();}
	
}
