import java.util.Scanner;

import core.AuthService;
import core.University;

import users.*;
import academic.*;
import enums.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static AuthService auth = new AuthService();
	static University uni = University.getInstance();
	static User currentUser;

	public static void main(String[] args) {

		while(true) {

			System.out.println("\n=== UNIVERSITY SYSTEM ===");

			if(currentUser == null) {
				System.out.println("1. Login");
				System.out.println("2. Exit");

				int c = sc.nextInt();

				if(c == 1) login();
				else if(c == 2) {
					uni.save();
					break;
				}
			}
			else {
				showMenu();
			}
		}
	}

	static void login() {
		System.out.print("username: ");
		String u = sc.next();
		System.out.print("password: ");
		String p = sc.next();

		currentUser = auth.login(u, p);

		if(currentUser == null)
			System.out.println("Wrong credentials");
		else
			System.out.println("Welcome " + currentUser.username);
	}

	static void showMenu() {

		System.out.println("\n1. View courses");
		System.out.println("2. Register course");
		System.out.println("3. View transcript");
		System.out.println("4. Logout");

		int c = sc.nextInt();

		if(c == 1) viewCourses();
		if(c == 2) registerCourse();
		if(c == 3) viewTranscript();
		if(c == 4) currentUser = null;
	}

	static void viewCourses() {
		for(Course c : uni.courses) {
			System.out.println(c.name + " (" + c.credits + ")");
		}
	}

	static void registerCourse() {
		if(!(currentUser instanceof Student)) return;

		System.out.print("Course name: ");
		String name = sc.next();

		for(Course c : uni.courses) {
			if(c.name.equals(name)) {
				try {
					((Student) currentUser).registerCourse(c);
					System.out.println("Registered!");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	static void viewTranscript() {
		if(currentUser instanceof Student) {
			((Student) currentUser).viewTranscript();
		}
	}
}
