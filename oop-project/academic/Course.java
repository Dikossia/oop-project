package academic;

import java.util.*;
import users.Student;
import users.Teacher;

public class Course {

    public String name;
    public String code;
    public int credits;
    public boolean isOpen;

    public List<Student> students;
    public List<Teacher> instructors;

    public Course(String name, String code, int credits) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.students = new ArrayList<Student>();
        this.instructors = new ArrayList<Teacher>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addInstructor(Teacher t) {
        instructors.add(t);
    }
}