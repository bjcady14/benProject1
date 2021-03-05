package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="students", schema="springdata")
@Data 
public class Student {
	
	public static List<Student> allStudents = new ArrayList<Student>();

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_Id", nullable=false, unique=true, updatable=false)
	private int id;
	
	private String name;
	private String email;
	private double gpa;
	
	@Value("false")
	private boolean academicProbation;
	
	@JsonManagedReference
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private Set<Course> courses;

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Student(int id, String name, String email, double gpa, boolean academicProbation, Set<Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gpa = gpa;
		this.academicProbation = academicProbation;
		this.courses = courses;
		allStudents.add(this);
	}

	public Student() {
		super();
		allStudents.add(this);
	}

	public Student(int id, String name, String email, double gpa, boolean academicProbation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gpa = gpa;
		this.academicProbation = academicProbation;
		allStudents.add(this);
	}

	public Student(String name, String email, double gpa) {
		super();
		this.name = name;
		this.email = email;
		this.gpa = gpa;
		allStudents.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public boolean isAcademicProbation() {
		return academicProbation;
	}

	public void setAcademicProbation(boolean academicProbation) {
		this.academicProbation = academicProbation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(academicProbation, courses, email, gpa, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return academicProbation == other.academicProbation && Objects.equals(courses, other.courses)
				&& Objects.equals(email, other.email)
				&& Double.doubleToLongBits(gpa) == Double.doubleToLongBits(other.gpa) && id == other.id
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", gpa=" + gpa + ", academicProbation="
				+ academicProbation + ", courses=" + courses + "]";
	}

	public void enroll(Course c) {
		this.courses.add(c);
	}
	
	public void dropCourse(Course c) {
		this.courses.remove(c);
	}
	
	
	
		
	

}
