package users;

import academic.Course;
import academic.Enrollment;
import academic.News;
import academic.Report;
import enums.ManagerType;
import core.University;

public class Manager extends Employee {
	public ManagerType type;

	public Manager() {
	}

	public void approveRegistration(Enrollment e) {
		System.out.println("Approved: " + e.student.username);
	}

	public void rejectRegistration(Enrollment e) {
		System.out.println("Rejected: " + e.student.username);
	}

	public void assignTeacher(Course c, Teacher t) {
		c.addInstructor(t);
	}

	public Report generateReport() {
		Report r = new Report();
		r.data = "Simple report";
		return r;
	}

	public void createCourse(Course c) {
		University uni = University.getInstance();
			uni.courses.add(c);
		System.out.println("Course created: " + c.name);
	}

	public void openCourseForRegistration(Course c) {
		c.isOpen = true;
	}

	public void manageNews(News n) {
		System.out.println("News: " + n.title);
	}
}
