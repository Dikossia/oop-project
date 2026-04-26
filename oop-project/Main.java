import java.util.Date;

import core.AuthService;
import core.Log;
import core.University;

import users.Admin;
import users.Student;
import users.Teacher;

import academic.Course;
import academic.Enrollment;

import enums.TeacherType;

public class Main {
	public static void main(String[] args) {

		University uni = University.getInstance();

		Admin admin = new Admin();
		admin.id = 1;
		admin.username = "admin";
		admin.password = "123";

		Student s = new Student(2, "alice", "111", "a@mail.com", 3.5, 2, 0, 0);

		Teacher t = new Teacher();
		t.id = 3;
		t.username = "bob";
		t.password = "222";
		t.title = TeacherType.PROFESSOR;
		t.hireDate = new Date();

		admin.addUser(admin);
		admin.addUser(s);
		admin.addUser(t);

		Course oop = new Course("OOP", "CS2001", 3);
		uni.courses.add(oop);

		oop.addInstructor(t);

		try {
			s.registerCourse(oop);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		Enrollment en = new Enrollment(s, oop);
		t.putMark(en.mark, 28, 27, 35);

		System.out.println("Student transcript:");
		s.viewTranscript();
		System.out.println(en.mark);

		AuthService auth = new AuthService();
		auth.login("alice", "111");

		uni.addLog(new Log(s, "Viewed transcript"));
		uni.addLog(new Log(t, "Put mark"));

		System.out.println();
		System.out.println("System logs:");
		uni.showLogs();
	}
}
