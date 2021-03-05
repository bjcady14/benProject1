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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="courses", schema="springdata")
@Data 
public class Course {
	
	public static List<Course> allCourses = new ArrayList<Course>();
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_Id", nullable=false, unique=true, updatable=false)
	private int id;
	
	private String name;
	private String professor;
	private String building;
	
	@JsonBackReference
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER, mappedBy="courses")
	private Set<Student> students;
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Course(int id, String name, String professor, String building, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.professor = professor;
		this.building = building;
		this.students = students;
		allCourses.add(this);
	}

	public Course() {
		super();
		allCourses.add(this);
	}

	public Course(int id, String name, String professor, String building) {
		super();
		this.id = id;
		this.name = name;
		this.professor = professor;
		this.building = building;
		allCourses.add(this);
	}
	
	

	public Course(String name, String professor, String building) {
		super();
		this.name = name;
		this.professor = professor;
		this.building = building;
		allCourses.add(this);
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	@Override
	public int hashCode() {
		return Objects.hash(building, id, name, professor, students);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		return Objects.equals(building, other.building) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(professor, other.professor) && Objects.equals(students, other.students);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", professor=" + professor + ", building=" + building
				+ ", students=" + students + "]";
	}

	public void addStudent(Student s) {
		this.students.add(s);
	}
	
	public void dropStudent(Student s) {
		this.students.remove(s);
	}
	
	
	
}
