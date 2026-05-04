package users;

import java.util.Scanner;
import service.University;

public class Admin extends Employee {

	private final Scanner sc = new Scanner(System.in);

	public Admin() {
	}
	public void addUser() {

	    System.out.print("Username: ");
	    String username = sc.nextLine();

	    System.out.print("Password: ");
	    String password = sc.nextLine();

	    System.out.print("Email: ");
	    String email = sc.nextLine();

	    Student s = new Student(100, username, password, email, 0, 1, 0, 0);


	    University.getInstance().users.add(s);
	    System.out.println("User added: " + s.username);
	}

	public void removeUser() {

	    System.out.print("Username: ");
	    String username = sc.nextLine();


	    University uni = University.getInstance();

	    User target = null;

	    for (User u : uni.users) {
		if (u.username.equals(username)) {
		    target = u;
		    break;
		}
	    }

	    if (target == null) {
		System.out.println("User not found");
		return;
	    }

	    uni.users.remove(target);

	    System.out.println("User removed: " + target);
	}

	public void updateLogs() {
		System.out.println("Logs updated");
	}

	public void blockUser(User user) {
		System.out.println(user.username + " blocked");
	}

	public void assignRole(User user) {
		System.out.println("Role assigned to " + user.username);
	}
}
