package academic;

import java.util.Date;

public class News {
	public String title;
	public String content;
	public Date date;

	public String toString() {
		return title + " : " + content;
	}
}
