package core;

import java.util.Date;
import users.User;

public class Log {
	public User user;
	public String action;
	public Date date;

	public Log(User user, String action) {
		this.user = user;
		this.action = action;
		this.date = new Date();
	}

	public String toString() {
		return date + " : " + user.username + " -> " + action;
	}
}
