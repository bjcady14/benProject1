package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {
	
	public Optional<Student> findByName(String name);
	
	public void removeByName(String s);
	
//	public void removeAll();
	
	@Modifying
	@Query(value="UPDATE springdata.students SET academic_probation=true where gpa<2.0", nativeQuery=true)
	public void updateAcademicStatus();

	@Query(value="select avg(gpa) from springdata.students", nativeQuery=true)
	public Optional<Double> findAvgGpa();

	@Query(value="select avg(gpa) from springdata.students where gpa>2.0", nativeQuery=true)
	public Optional<Double> findAvgGpaSome();
	
//	@Modifying
//	@Query(value="delete from springdata.students where gpa<2.0")
//	public void expel();
	
}
