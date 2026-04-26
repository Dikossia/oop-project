package users;

import java.io.*;
import java.util.*;
import academic.Course;
import academic.Lesson;
import academic.StudyMaterial;
import core.University;
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

	public void viewSchedule() {
		University uni = University.getInstance();
		System.out.println("Schedule for " + username + ":");
		for(Course course : uni.courses) {
			if(course.students.contains(this)) {
				System.out.println("- " + course.name + " (" + course.code + ")");
				if(course.lessons.isEmpty()) {
					System.out.println("  No lessons yet");
					continue;
				}
				for(Lesson lesson : course.lessons) {
					System.out.println("  " + lesson.getDisplayText());
				}
			}
		}
	}

	public void viewLearningFiles() {
		University uni = University.getInstance();
		System.out.println("Learning files and tasks for " + username + ":");
		for(Course course : uni.courses) {
			if(course.students.contains(this)) {
				System.out.println("- " + course.name + " (" + course.code + ")");
				if(course.materials.isEmpty()) {
					System.out.println("  No uploaded materials yet");
					continue;
				}
				for(StudyMaterial material : course.materials) {
					System.out.println("  " + material);
				}
			}
		}
	}

	public void solveTask(StudyMaterial task, String solutionText) {
		if(task == null || !task.isTask) {
			System.out.println("Selected material is not a task");
			return;
		}
		if(task.course == null || !task.course.students.contains(this)) {
			System.out.println("You are not enrolled in this course");
			return;
		}
		task.submitSolution(this, solutionText);
		System.out.println("Solution submitted for task: " + task.title);
	}
}
