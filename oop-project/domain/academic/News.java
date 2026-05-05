package academic;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
	public String title;
	public String content;
	public Date date;
	public String author;

	public News (String title, String content, Date date) {
		this.title = title;
		this.content = content;
		this.date = date;
		this.author = "Dean Office";
	}
	public News (String title, String content, Date date, String author) {
		this.title = title;
		this.content = content;
		this.date = date;
		this.author = author;
	}

	public String toString() {
		return title + " : " + content;
	}
}
