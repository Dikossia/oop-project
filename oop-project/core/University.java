package core;

import java.util.ArrayList;
import java.util.List;

import users.User;
import users.Student;
import academic.Course;
import research.ResearchProject;

public class University {
	private static University instance = new University();

	public List<User> users;
	public List<Course> courses;
	public List<ResearchProject> projects;
	public List<Log> logs;

	private University() {
		users = new ArrayList<User>();
		courses = new ArrayList<Course>();
		projects = new ArrayList<ResearchProject>();
		logs = new ArrayList<Log>();
	}

	public static University getInstance() {
		return instance;
	}

	public void addLog(Log log) {
		logs.add(log);
	}

	public void showLogs() {
		for(Log l : logs) {
			System.out.println(l);
		}
	}

	public Student findStudentById(int sId){
		for(User user : users){
			if (user instanceof Student && user.id == sId){
				return (Student) user;
			}
		}
		return null;
	}
}
