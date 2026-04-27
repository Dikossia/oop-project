package core;

import core.*;
import users.*;
import academic.*;
import enums.*;

import java.util.*;

public class ConsoleUIController {

    private final Scanner sc = new Scanner(System.in);
    private final University uni = University.getInstance();
    private final AuthService authService = new AuthService();
    private User currentUser;

    public void start() {
        uni.courses.addAll(DataLoader.loadCourses("courses.txt"));
	uni.users.addAll(DataLoader.loadUsers("users.txt"));
        DataLoader.loadSchedule();
        seedUsers();

        while (true) {
            if (currentUser == null) {
                loginMenu();
            } else {
                roleMenu();
            }
        }
    }

    // ================= LOGIN =================
    private void loginMenu() {
        System.out.println("\n=== UNIVERSITY SYSTEM ===");
        System.out.println("1. Login");
        System.out.println("0. Exit");

        int ch = readInt();

        if (ch == 0) {
            DataLoader.saveCourses(uni.courses);
            DataLoader.saveSchedule();
	    DataLoader.saveUsers(uni.users, "users.txt");
            System.exit(0);
        }

        if (ch == 1) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();

            currentUser = authService.login(u, p);

            if (currentUser == null) {
                System.out.println("Invalid credentials");
            }
        }
    }

    // ================= ROLE ROUTER =================
    private void roleMenu() {
        if (currentUser instanceof Admin) adminMenu();
        else if (currentUser instanceof Student) studentMenu();
        else if (currentUser instanceof Teacher) teacherMenu();
        else if (currentUser instanceof Manager) managerMenu();
    }

    // ================= ADMIN =================
	private void adminMenu() {

	    Admin admin = (Admin) currentUser;

	    while (true) {

		System.out.println("\n-- ADMIN MENU --");
		System.out.println("1. Show Users");
		System.out.println("2. Add User");
		System.out.println("3. Remove User");
		System.out.println("4. Show Logs");
		System.out.println("5. Logout");

		int ch = readInt();

		if (ch == 5) {
		    currentUser = null;
		    return;
		}

		if (ch == 1) {
		    for (User u : uni.users) {
			System.out.println(u);
		    }
		}

		if (ch == 2) {
		    admin.addUser();
		}

		if (ch == 3) {
		    admin.removeUser();
		}

		if (ch == 4) {
		    uni.showLogs();
		}
	    }
	}
    // ================= STUDENT =================
    private void studentMenu() {
        Student s = (Student) currentUser;

        while (true) {
            System.out.println("\n-- STUDENT MENU --");
            System.out.println("1. View Schedule");
            System.out.println("2. View Transcript");
            System.out.println("3. Register First Course");
            System.out.println("4. Logout");

            int ch = readInt();

            if (ch == 4) {
                currentUser = null;
                return;
            }

            try {
                if (ch == 1) s.viewSchedule();
                if (ch == 2) s.viewTranscript();
                if (ch == 3 && !uni.courses.isEmpty()) {
                    s.registerCourse(uni.courses.get(0));
                    System.out.println("Registered");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // ================= TEACHER =================
    private void teacherMenu() {
        Teacher t = (Teacher) currentUser;

        while (true) {
            System.out.println("\n-- TEACHER MENU --");
            System.out.println("1. View Courses");
            System.out.println("2. Add Lesson");
            System.out.println("3. Logout");

            int ch = readInt();

            if (ch == 3) {
                currentUser = null;
                return;
            }

            if (ch == 1) t.viewStudents();

            if (ch == 2 && !uni.courses.isEmpty()) {
                Calendar c = Calendar.getInstance();
                Lesson l = new Lesson(c.getTime(), "AUTO", LessonType.LECTURE);
                t.addLessonToCourse(uni.courses.get(0), l);
                System.out.println("Lesson added");
            }
        }
    }

    // ================= MANAGER =================
    private void managerMenu() {
        while (true) {
            System.out.println("\n-- MANAGER MENU --");
            System.out.println("1. Open Courses");
            System.out.println("2. Show Courses");
            System.out.println("3. Logout");

            int ch = readInt();

            if (ch == 3) {
                currentUser = null;
                return;
            }

            if (ch == 1) {
                for (Course c : uni.courses) c.isOpen = true;
            }

            if (ch == 2) {
                for (Course c : uni.courses)
                    System.out.println(c.name + " " + c.code);
            }
        }
    }

    // ================= HELPERS =================
    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    // ================= SEED USERS =================
    private void seedUsers() {
        Admin a = new Admin(); a.id = 1; a.username = "admin"; a.password = "123";
        Student s = new Student(2,"alice","111","a@mail.com",3.5,2,0,0);
        Teacher t = new Teacher(); t.id = 3; t.username = "bob"; t.password = "222";
        Manager m = new Manager(); m.id = 4; m.username = "manager"; m.password = "333";

        uni.users.add(a);
        uni.users.add(s);
        uni.users.add(t);
        uni.users.add(m);
    }
}
