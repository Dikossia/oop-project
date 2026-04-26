package academic;

import java.util.ArrayList;
import java.util.List;

import users.Student;
import users.Teacher;

public class Course {
	public String name;
	public String code;
	public int credits;
	public boolean isOpen;
	public int allowedYear;

	public List<Student> students;
	public List<Teacher> teachers;

	public Course() {
		students = new ArrayList<Student>();
		teachers = new ArrayList<Teacher>();
	}

	public Course(String name, String code, int credits) {
		this();
		this.name = name;
		this.code = code;
		this.credits = credits;
	}

	public void addStudent(Student s) {
		students.add(s);
	}

	public void addInstructor(Teacher t) {
		teachers.add(t);
		t.courses.add(this);
	}
}
