package core;

import users.User;

public class AuthService {

	public User login(String username, String password) {
		for(User u : University.getInstance().users) {
			if(u.username.equals(username) && u.checkPassword(password)) {
				return u;
			}
		}
		return null;
	}

	public void logout(User user) {
		if(user != null) {
			System.out.println(user.username + " logged out");
		}
	}
}
