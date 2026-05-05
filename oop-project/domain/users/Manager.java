package users;

import java.util.List;
import java.util.ArrayList;
import users.User;

import academic.Course;
import academic.Enrollment;
import academic.News;
import academic.Report;
import academic.Request;
import enums.ManagerType;
import enums.RequestStatus;
import core.University;



public class Manager extends Employee {
	public ManagerType type;
	private static List<News> news;

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
		University uni = University.getInstance();
		int marksCount = 0;
		double total = 0.0;
		String bestStudent = "N/A";
		double bestAverage = -1.0;

		for(User user : uni.users) {
			if(!(user instanceof Student)) {
				continue;
			}
			Student student = (Student) user;
			double studentTotal = 0.0;
			int studentMarks = 0;
			for(Enrollment enrollment : student.enrollments) {
				studentTotal += enrollment.mark.getTotal();
				studentMarks++;
				total += enrollment.mark.getTotal();
				marksCount++;
			}
			if(studentMarks > 0) {
				double avg = studentTotal / studentMarks;
				if(avg > bestAverage) {
					bestAverage = avg;
					bestStudent = student.username;
				}
			}
		}

		double overallAverage = marksCount == 0 ? 0.0 : total / marksCount;
		r.data = "Total marks records: " + marksCount
				+ "\nOverall average: " + overallAverage
				+ "\nTop student by average mark: " + bestStudent;
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
		University.getInstance().publishNews(n);
	}

	public void manageNews(News n) {
		System.out.println("News: " + n.title);
	}

	public void showRequests(){
		if(requests.isEmpty()){
			System.out.println("No Requests");
		}
		else {
		for(Request r : Employee.requests){
			System.out.println("Request ID: " + r.requestId);
			System.out.println("id: " + r.senderId);
			System.out.println("type: " + r.type);
			System.out.println("status: " + r.status);
			System.out.println("description: " + r.description);
			System.out.println();
		}
		}
	}

	public Request findRequestById(long rId){
		for(Request r : requests){
			if(r.requestId == rId){
				return r;
			}
		}
		return null;
	}

	public void showRequest(Request r){
		System.out.println("Request ID: " + r.requestId);
		System.out.println("id: " + r.senderId);
		System.out.println("type: " + r.type);
		System.out.println("status: " + r.status);
		System.out.println("description: " + r.description);
		System.out.println();
	}

	public void reviewRequest(Request r, boolean status){
		if(status){ r.status = RequestStatus.APPROVED;}
		else{r.status = RequestStatus.REJECTED;}
	}
	public void removeRequest(long rId){
		Request r = this.findRequestById(rId);
		requests.remove(r);
	}

	public void showFinishedRequests(){
		for(Request r : requests){
			if(r.status != RequestStatus.PENDING){
				this.showRequest(r);
			}
		}
	}


}
