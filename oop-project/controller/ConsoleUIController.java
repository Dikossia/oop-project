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
		uni.courses.addAll(DataLoader.loadCourses("./data/courses.txt"));
		uni.users.addAll(DataLoader.loadUsers("./data/users.txt"));
		DataLoader.loadSchedule2("./data/schedule2.txt");
		DataLoader.loadRequests();
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
			DataLoader.saveSchedule2("./data/schedule2.txt");
			DataLoader.saveRequests();
			DataLoader.saveUsers(uni.users, "./data/users.txt");
			System.exit(0);
		}

		if (ch == 1) {
			System.out.print("Username: ");
			String u = sc.nextLine();
			System.out.print("Password: ");
			String p = sc.nextLine();

			currentUser = authService.login(u, p);

			if(currentUser == null){
				System.out.println("Invalid Credentials");
			}
			else{
				Log l = new Log(currentUser, "logged in");
				uni.addLog(l);
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
			System.out.println("6. Send Request");
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
			if (ch == 6) {
				admin.addRequest();
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
				Log l = new Log(currentUser, "Logout");
				uni.addLog(l);
				currentUser = null;
				return;
			}

			try {
				if (ch == 1) {
					s.viewSchedule();
					Log l = new Log(s, "Viewed Schedule");
					uni.addLog(l);
				}
				if (ch == 2) {
					s.viewTranscript();
					Log l = new Log(s, "Viewed Transcript");
					uni.addLog(l);
				}
				if (ch == 3 && !uni.courses.isEmpty()) {
					for (int i = 0; i < uni.courses.size(); i++) {
						System.out.println(uni.courses.get(i));
					}
					System.out.println("Enter name of an " +
							"interested course: ");
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
			System.out.println("6. Send Request");

			int ch = readInt();

			if (ch == 3) {
				currentUser = null;
				return;
			}

			if (ch == 6) {
				t.addRequest();
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
		Manager m = (Manager) currentUser;
		while (true) {
			System.out.println("\n-- MANAGER MENU --");
			System.out.println("1. Open Courses");
			System.out.println("2. Show Courses");
			System.out.println("3. Logout");
			System.out.println("4. Create Course");
			System.out.println("5. Add News");
			System.out.println("6. Send Request");
			System.out.println("7. Show Requests");
			System.out.println("8. Remove Request");
			System.out.println("9. Review Request");
			System.out.println("10. Finished Requests");
			System.out.println();

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

			if (ch == 4) {
				System.out.println("Course Name: ");
				String cName = sc.nextLine();
				System.out.println("Course Code: ");
				String cCode = sc.nextLine();
				System.out.println("Course Credits: ");
				int cCredits = sc.nextInt();

				Course c = new Course(cName, cCode, cCredits);
				m.createCourse(c);
			}

			if (ch == 6) {
				m.addRequest();
			}

			if (ch == 7) {
				m.showRequests();
			}

			if (ch == 8) {
				System.out.println("Enter Request ID");
				long rId = sc.nextInt();
				m.removeRequest(rId);
			}

			if (ch == 9) {
				System.out.println("Enter Request ID");
				long rId = sc.nextInt();
				Request r = m.findRequestById(rId);
				m.showRequest(r);
				sc.nextLine();
				System.out.println("Enter New Request Status");
				String rStatus = sc.nextLine();
				r.status = RequestStatus.valueOf(rStatus);
				System.out.println("Status have been Changed");
			}

			if (ch == 10) {
				m.showFinishedRequests();
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
	};

	/*
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
	*/
}
