package users;

import java.util.List;
import java.util.ArrayList;

import academic.Course;
import academic.Enrollment;
import academic.News;
import academic.Report;
import academic.Request;
import enums.ManagerType;
import core.University;



public class Manager extends Employee {
	public ManagerType type;
	private static List<News> news;
	private static List<Report> reports;

	public Manager() {
		news = new ArrayList<News>();
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

	public void createNews(News n) {
		news.add(n);
	}

	public void manageNews(News n) {
		System.out.println("News: " + n.title);
	}

	public void showRequests(){
		if(requests.isEmpty()){
			System.out.println("No Requests");
		}
		else {
		for(Request r : requests){
			System.out.println("id: " + r.id);
			System.out.println("type: " + r.type);
			System.out.println("status: " + r.status);
			System.out.println("description: " + r.description);
		}
		}
	}


}
