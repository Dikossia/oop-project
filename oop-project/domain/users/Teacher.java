package users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import academic.Course;
import academic.Enrollment;
import academic.Lesson;
import academic.Mark;
import academic.StudyMaterial;
import research.ResearchPaper;
import research.Researcher;
import enums.TeacherType;
import enums.UserType;

public class Teacher extends Employee implements Researcher {
	public TeacherType title;
	public List<Course> courses;
	public List<ResearchPaper> papers;
	private int hIndex;
	public final static UserType userType = UserType.Teacher;

	public Teacher() {
		courses = new ArrayList<Course>();
		papers = new ArrayList<ResearchPaper>();
		hIndex = 0;
	}

	public void putMark(Mark mark, double a1, double a2, double fin) {
		mark.att1 = a1;
		mark.att2 = a2;
		mark.finalExam = fin;
	}

	public void putMark(Course course, Student student, double a1, double a2, double fin) {
		Enrollment enrollment = course.findEnrollment(student);
		if(enrollment == null) {
			throw new IllegalStateException("Student is not enrolled in this course");
		}
		putMark(enrollment.mark, a1, a2, fin);
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

	@Override
	public int getHIndex() {
		return hIndex;
	}

	@Override
	public void updateHIndex()
	{
		int paperCount = papers.size();
		hIndex = 0;

		for (ResearchPaper paper: papers)
		{
			if (paper.getCitations() >= paperCount)
			{
				hIndex++;
			}
		}
	}

	@Override
	public boolean addPaper(ResearchPaper paper)
	{
		for (ResearchPaper rp: papers)
		{
			if (rp.getDoi().equals(paper.getDoi()))
			{
				return false;
			}
		}

		papers.add(paper);
		updateHIndex();

		return true;
	}

	public List<ResearchPaper> getPapers() {
		return papers;
	}

	public void printPapers(Comparator<ResearchPaper> c) {
		papers.stream()
				.sorted(c)
				.forEach((p) -> System.out.println("Title: " + p.getTitle()));
	}
}
