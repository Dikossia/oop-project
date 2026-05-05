package academic;

import java.io.Serializable;
import users.Student;

public class Enrollment implements Serializable {
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
