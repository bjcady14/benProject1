package com.revature.controllers;

import java.util.Set;
import javax.transaction.Transactional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.models.Student;
import com.revature.services.StudentService;

@RestController @Transactional
@RequestMapping("/students")
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService studServ;
	
	@GetMapping
	public ResponseEntity<Set<Student>> findAll(){
		Set<Student> allStuds = studServ.findAll();
		MDC.put("event", "Finding all students");
		if(allStuds.isEmpty()) {
			log.info("No students found"); 
			ResponseEntity<Set<Student>> resp = ResponseEntity.noContent().build();
			MDC.clear();
			return resp;  
		}
		log.info("Found all students"); MDC.remove("event");
		ResponseEntity<Set<Student>> resp = ResponseEntity.ok(allStuds);
		MDC.clear();
		return resp;
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Student> findByName(@PathVariable(name="name") String name){
		MDC.put("event", "Finding student");
		log.info("Search for Student: "+name);	
		ResponseEntity<Student> response = ResponseEntity.ok(studServ.findByName(name));
		MDC.clear();
		return response;
	}
		
	@PostMapping(produces= {"application/json"}, consumes= {"application/json;charset=UTF-8"})
	public ResponseEntity<Student> insert(@RequestBody Student s){
		MDC.put("event","Add student"); log.info("Added student: " + s.getName()); MDC.remove("event");
		ResponseEntity<Student> response = ResponseEntity.ok(studServ.insert(s));
		MDC.clear();
		return response;
	}
	
	@Transactional
	@DeleteMapping("/{n}")
	public void delete(@PathVariable(name="n") String n){	
		MDC.put("event", "Remove student"); log.info("Removing student: " + n);
		studServ.removeByName(n); MDC.clear();
	}
	
	@Modifying
	@PutMapping("/status")
	public void updateAcademicStatus() { 
		MDC.put("event", "Update Status"); log.info("Updating academic standing"); 
		studServ.updateAcademicStatus(); MDC.clear();
		}
	 
//	@DeleteMapping("/noProbation")
//	public void expel() { studServ.expell(); }
	
	@GetMapping("/gpa/{category}")
	public ResponseEntity<String> findAvgGpa(@PathVariable(name="category") String cat){
		MDC.put("event", "Find Avg GPA"); 
		if(cat.equals("all")) {
			log.info("Finding average GPA for all students"); 
			ResponseEntity<String> resp = ResponseEntity.ok(studServ.findAvgGpa("all"));
			MDC.clear();
			return resp;
		} else if(cat.equals("some")) {
			log.info("Finding average GPA for all students not on academic probation"); 
			ResponseEntity<String> resp= ResponseEntity.ok(studServ.findAvgGpa("some"));
			MDC.clear();
			return resp;
		}
		log.info("Could not find average GPA: invalid path parameter");
		ResponseEntity<String> resp = ResponseEntity.ok("Enter valid path parameter: all/some");
		MDC.clear();
		return resp;
	}     
	
	@Transactional
	@DeleteMapping("/truncate")
	public void deleteAll(){	
		MDC.put("event", "Remove All"); 
		log.info("Removing all students");
		studServ.removeAll();
		MDC.clear();
	}
	
	
	
}
