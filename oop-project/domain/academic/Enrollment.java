package academic;

import users.Student;

public class Enrollment {
	public Student student;
	public Course course;
	public Mark mark;

	public Enrollment() {
		mark = new Mark();
	}

	public Enrollment(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.mark = new Mark();
	}
}
