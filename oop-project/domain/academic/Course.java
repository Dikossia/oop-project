package academic;

import java.io.Serializable;
import java.util.*;
import users.Student;
import users.Teacher;

public class Course implements Serializable {

    public String name;
    public String code;
    public int credits;
    public boolean isOpen;

    public List<Student> students;
    public List<Teacher> instructors;
    public List<Lesson> lessons;
    public List<StudyMaterial> materials;
    public List<Enrollment> enrollments;

    public Course(String name, String code, int credits) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.students = new ArrayList<Student>();
        this.instructors = new ArrayList<Teacher>();
        this.lessons = new ArrayList<Lesson>();
        this.materials = new ArrayList<StudyMaterial>();
        this.enrollments = new ArrayList<Enrollment>();
    }

    public void addStudent(Student s) {
        if(!students.contains(s)) {
            students.add(s);
        }
    }

    public void addInstructor(Teacher t) {
        instructors.add(t);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addMaterial(StudyMaterial material) {
        materials.add(material);
    }

    public Enrollment findEnrollment(Student student) {
        for(Enrollment enrollment : enrollments) {
            if(enrollment.student == student) {
                return enrollment;
            }
        }
        return null;
    }
}