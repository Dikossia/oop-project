package users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import academic.Course;
import academic.Lesson;
import academic.Mark;
import academic.StudyMaterial;
import research.ResearchPaper;
import research.Researcher;
import enums.TeacherType;

public class Teacher extends Employee implements Researcher {
	public TeacherType title;
	public List<Course> courses;
	public List<ResearchPaper> papers;

	public Teacher() {
		courses = new ArrayList<Course>();
		papers = new ArrayList<ResearchPaper>();
	}

	public void putMark(Mark mark, double a1, double a2, double fin) {
		mark.att1 = a1;
		mark.att2 = a2;
		mark.finalExam = fin;
	}

	public void viewStudents() {
		for(Course c : courses) {
			System.out.println(c.name);
		}
	}

	public void addLessonToCourse(Course course, Lesson lesson) {
		if(!courses.contains(course)) {
			courses.add(course);
		}
		course.addLesson(lesson);
	}

	public StudyMaterial uploadMaterial(Course course, String title, String description, String fileName, Date deadline, boolean isTask) {
		if(!courses.contains(course)) {
			courses.add(course);
		}
		StudyMaterial material = new StudyMaterial(title, description, fileName, deadline, isTask, this, course);
		course.addMaterial(material);
		return material;
	}

	public int getHIndex() {
		return papers.size();
	}

	public List<ResearchPaper> getPapers() {
		return papers;
	}

	public void printPapers(Comparator<ResearchPaper> c) {
		papers.sort(c);
		for(ResearchPaper p : papers) {
			System.out.println(p);
		}
	}
}
