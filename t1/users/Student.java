package users;

import java.io.*;
import java.util.*;
import academic.Course;
import exceptions.CreditLimitExceededException;

public class Student extends User {
	public double gpa;
	public int year;
	public int credits;
	public int failedCourses;

	public Student() {
	}

	public Student(int id, String username, String password, String email, double gpa, int year, int credits, int failedCourses) {
		super(id, username, password, email);
		this.gpa = gpa;
		this.year = year;
		this.credits = credits;
		this.failedCourses = failedCourses;
	}

	public void registerCourse(Course course) throws CreditLimitExceededException {
		if(credits + course.credits > 21) {
			throw new CreditLimitExceededException("Credit limit exceeded");
		}
		credits += course.credits;
		course.addStudent(this);
	}

	public void viewTranscript() {
		System.out.println("Student: " + username + " GPA: " + gpa);
	}
}
