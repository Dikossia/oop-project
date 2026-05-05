package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import academic.News;

public abstract class User implements Serializable {
	public int id;
	public String username;
	public String password;
	public String email;
	public List<News> inboxNews = new ArrayList<News>();

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

	public void receiveNews(News news) {
		inboxNews.add(news);
	}
}
