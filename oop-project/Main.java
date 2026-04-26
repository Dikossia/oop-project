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
        if(uni.courses.isEmpty()) {
            Course fallbackCourse = new Course("Object Oriented Programming", "CS101", 3);
            fallbackCourse.isOpen = true;
            uni.courses.add(fallbackCourse);
        }

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.APRIL, 27, 10, 0);
        Lesson lecture = new Lesson(calendar.getTime(), "B-201", LessonType.LECTURE);
        t.addLessonToCourse(course, lecture);

        calendar.set(2026, Calendar.APRIL, 29, 12, 0);
        Lesson practice = new Lesson(calendar.getTime(), "LAB-5", LessonType.PRACTICE);
        t.addLessonToCourse(course, practice);

        System.out.println("Schedule created for course");


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

        calendar.set(2026, Calendar.MAY, 5, 23, 59);
        t.uploadMaterial(course, "Week 1 slides", "Introduction to course topics", "week1.pdf", null, false);
        StudyMaterial hw1 = t.uploadMaterial(course, "Homework 1", "Solve OOP exercises", "hw1.docx", calendar.getTime(), true);
        System.out.println("Teacher uploaded files and tasks");

        System.out.println("\n=== STUDENT SCHEDULE ===");
        s.viewSchedule();

        System.out.println("\n=== LEARNING FILES ===");
        s.viewLearningFiles();

        s.solveTask(hw1, "My homework solution text");
        System.out.println("Saved solution: " + hw1.getSubmission(s.id));


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

        List<Researcher> authors = new ArrayList<Researcher>();
        authors.add(t);
        ResearchPaper p1 = new ResearchPaper("10.1000/ai-1", "AI", "Journal of AI", 10, authors);
        ResearchPaper p2 = new ResearchPaper("10.1000/ml-1", "ML", "Journal of ML", 50, authors);
        p1.addCitation();
        p1.addCitation();
        p2.addCitation();
        p2.addCitation();
        p2.addCitation();

        t.papers.add(p1);
        t.papers.add(p2);

        System.out.println("Sorted papers by citations:");

        t.printPapers(new Comparator<ResearchPaper>() {
            public int compare(ResearchPaper a, ResearchPaper b) {
                return b.getCitations() - a.getCitations();
            }
        });


        ResearchProject rp = new ResearchProject("Deep Learning");
        try {
            rp.addParticipant(t);
        } catch(Exception e) {
            System.out.println("Cannot add participant: " + e.getMessage());
        }
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