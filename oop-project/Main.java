import java.util.*;

import core.*;
import users.*;
import academic.*;
import enums.*;
import research.*;

public class Main {

    public static void main(String[] args) {

        University uni = University.getInstance();

        System.out.println("=== START SYSTEM ===");


        List<Course> courses = DataLoader.loadCourses("courses.txt");
        uni.courses.addAll(courses);

        System.out.println("Courses loaded:");
        for(Course c : uni.courses) {
            System.out.println("- " + c.name + " (" + c.code + ")");
        }


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


        Manager m = new Manager();
        m.id = 4;
        m.username = "manager";
        m.password = "333";


        admin.addUser(admin);
        admin.addUser(s);
        admin.addUser(t);
        admin.addUser(m);

        System.out.println("Users created");


        Course course = uni.courses.get(0);

        course.addInstructor(t);
        t.courses.add(course);

        System.out.println("Teacher assigned to course: " + course.name);


        Enrollment en = new Enrollment(s, course);

        try {
            s.registerCourse(course);
            m.approveRegistration(en);
            System.out.println("Student registered to course");
        } catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }


        t.putMark(en.mark, 30, 30, 40);

        System.out.println("Marks added");


        System.out.println("\n=== TRANSCRIPT ===");
        s.viewTranscript();
        System.out.println(en.mark);


        AuthService auth = new AuthService();
        auth.login("alice", "111");


        uni.addLog(new Log(s, "Registered to course"));
        uni.addLog(new Log(t, "Put mark"));


        System.out.println("\n=== LOGS ===");
        uni.showLogs();


        System.out.println("\n=== RESEARCH ===");

        ResearchPaper p1 = new ResearchPaper("AI", 10);
        ResearchPaper p2 = new ResearchPaper("ML", 50);

        t.papers.add(p1);
        t.papers.add(p2);

        System.out.println("Sorted papers by citations:");

        t.printPapers(new Comparator<ResearchPaper>() {
            public int compare(ResearchPaper a, ResearchPaper b) {
                return b.citations - a.citations;
            }
        });


        ResearchProject rp = new ResearchProject("Deep Learning");
        rp.addParticipant(t);
        rp.addPaper(p1);

        uni.projects.add(rp);

        System.out.println("Research project created");


        System.out.println("\n=== EXCEPTION TEST ===");

        try {
            for(int i = 0; i < 10; i++) {
                s.registerCourse(course);
            }
        } catch(Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }


        System.out.println("\n=== END SYSTEM ===");
    }
}