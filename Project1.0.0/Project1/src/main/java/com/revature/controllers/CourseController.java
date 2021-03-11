package com.revature.controllers;

import java.util.Set;

import javax.transaction.Transactional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Course;
import com.revature.services.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private CourseService cServ;
	
	@GetMapping
	public ResponseEntity<Set<Course>> findAll(){
		Set<Course> allCourses = cServ.findAll();
		MDC.put("event", "find all courses");
		if(allCourses.isEmpty()) {
			log.info("no courses found!");
			ResponseEntity<Set<Course>> resp = ResponseEntity.noContent().build();
			MDC.clear();
			return resp;
		}
		log.info("found all courses!");
		ResponseEntity<Set<Course>> resp = ResponseEntity.ok(allCourses);
		MDC.clear();
		return resp;
	}
	
	@PostMapping(produces= {"application/json"}, consumes= {"application/json;charset=UTF-8"})
	public ResponseEntity<Course> insert(@RequestBody Course c){
		MDC.put("event", "adding course");
		log.info("Adding course: "+ c.getName());
		ResponseEntity<Course> resp = ResponseEntity.ok(cServ.insert(c));
		MDC.clear();
		return resp;
	}
	
	@Transactional
	@DeleteMapping("/{c}")
	public void delete(@PathVariable(name="c") String c){	
		MDC.put("event", "deleting course");
		log.info("deleting course: "+ c);
		cServ.removeByName(c);
		MDC.clear();
	}
	
	
	@GetMapping("/Building/{building}")
	public ResponseEntity<Set<Course>> findByBuilding(@PathVariable(name="building") String building){
		MDC.put("event", "find by building");
		log.info("Finding all courses in " + building);
		ResponseEntity<Set<Course>> resp = ResponseEntity.ok(cServ.findByBuilding(building));
		MDC.clear();
		return resp;
	}
	
	
}
