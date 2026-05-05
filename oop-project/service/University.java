package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import users.User;
import core.Log;
import users.Student;
import users.Employee;
import academic.Course;
import academic.News;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;

public class University implements Serializable {
	private static University instance = new University();

	public List<User> users;
	public List<Course> courses;
	public List<ResearchProject> projects;
	public List<Log> logs;
	public List<News> newsFeed;

	private University() {
		users = new ArrayList<User>();
		courses = new ArrayList<Course>();
		projects = new ArrayList<ResearchProject>();
		logs = new ArrayList<Log>();
		newsFeed = new ArrayList<News>();
	}

	public static University getInstance() {
		return instance;
	}

	public static void setInstance(University loaded) {
		if(loaded != null) {
			instance = loaded;
		}
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

	public Employee findEmployeeById(int eId){
		for(User user: users){
			if (user instanceof Employee && user.id == eId){
				return (Employee) user;
			}
		}
		return null;
	}

	public List<Researcher> getAllResearchers() {
		List<Researcher> researchers = new ArrayList<Researcher>();
		for(User user : users) {
			if(user instanceof Researcher) {
				researchers.add((Researcher) user);
			}
		}
		return researchers;
	}

	public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
		List<ResearchPaper> allPapers = new ArrayList<ResearchPaper>();
		for(Researcher researcher : getAllResearchers()) {
			allPapers.addAll(researcher.getPapers());
		}
		allPapers.stream().sorted(comparator).forEach(System.out::println);
	}

	public Researcher getTopCitedResearcher() {
		Researcher top = null;
		int maxCitations = -1;
		for(Researcher researcher : getAllResearchers()) {
			int totalCitations = 0;
			for(ResearchPaper paper : researcher.getPapers()) {
				totalCitations += paper.getCitations();
			}
			if(totalCitations > maxCitations) {
				maxCitations = totalCitations;
				top = researcher;
			}
		}
		return top;
	}

	public void publishNews(News news) {
		newsFeed.add(news);
		for(User user : users) {
			user.receiveNews(news);
		}
	}
}
