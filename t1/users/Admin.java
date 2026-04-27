package users;

import core.University;

public class Admin extends Employee {

	public Admin() {
	}

	public void addUser(User user) {
		University.getInstance().users.add(user);
	}

	public void removeUser(User user) {
		University.getInstance().users.remove(user);
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
