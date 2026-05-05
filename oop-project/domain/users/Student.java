package users;

import academic.Course;
import academic.Enrollment;
import academic.Schedule;
import academic.ScheduleEntry;
import academic.StudyMaterial;
import exceptions.CreditLimitExceededException;
import exceptions.LowHIndexException;
import research.Researcher;
import core.University;
import enums.UserType;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
	public static final int MAX_CREDITS = 21;
	public double gpa;
	public int year;
	public int credits;
	public int failedCourses;
	public static final UserType userType = UserType.Student;
	public Schedule schedule;
	public List<Enrollment> enrollments;
	public Researcher researchSupervisor;

	public Student() {
		enrollments = new ArrayList<Enrollment>();
	}

	public Student(int id, String username, String password, String email,
			double gpa, int year, int credits, int failedCourses) {
		super(id, username, password, email);
		this.gpa = gpa;
		this.year = year;
		this.credits = credits;
		this.failedCourses = failedCourses;
		schedule = new Schedule();
		enrollments = new ArrayList<Enrollment>();
	}

	public void registerCourse(Course course) throws CreditLimitExceededException {
		if(course == null || !course.isOpen) {
			throw new IllegalStateException("Course is closed for registration");
		}
		if(failedCourses > 3) {
			throw new IllegalStateException("Registration is blocked: too many failed courses");
		}
		if(course.findEnrollment(this) != null) {
			throw new IllegalStateException("Already registered to this course");
		}
		if(credits + course.credits > MAX_CREDITS) {
			throw new CreditLimitExceededException("Credit limit exceeded");
		}
		credits += course.credits;
		course.addStudent(this);
		Enrollment enrollment = new Enrollment(this, course);
		course.enrollments.add(enrollment);
		enrollments.add(enrollment);
	}

	public void viewTranscript() {
		System.out.println("Student: " + username + " GPA: " + gpa);
		if(enrollments.isEmpty()) {
			System.out.println("No registered courses yet");
			return;
		}
		for(Enrollment enrollment : enrollments) {
			System.out.println(enrollment.course.name + " -> " + enrollment.mark);
		}
	}

	public void assignResearchSupervisor(Researcher supervisor) throws LowHIndexException {
		if(year < 4) {
			researchSupervisor = supervisor;
			return;
		}
		if(supervisor == null || supervisor.getHIndex() < 3) {
			throw new LowHIndexException("4th year student must have a supervisor with h-index >= 3");
		}
		researchSupervisor = supervisor;
	}


	public void viewSchedule(){
		System.out.println("Schedule For " + username + ": ");
		if(this.schedule.scheduleEntries.isEmpty()) System.out.println("No Lessons Yet");
		else {
			for(ScheduleEntry entry : this.schedule.scheduleEntries){
				System.out.println(entry.courseName + " " + entry.teacherName +
							" " + entry.room.roomNumber);
			}
		}
		System.out.println("Finished");

	}
	/*
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
	*/

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
