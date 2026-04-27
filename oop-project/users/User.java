package users;

public abstract class User {
	public int id;
	public String username;
	public String password;
	public String email;

	public User() {
	}

	public User(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public String toString() {
		return id + " " + username + " " + email;
	}
}
