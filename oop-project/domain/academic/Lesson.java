package academic;

import java.text.SimpleDateFormat;
import java.util.Date;
import enums.LessonType;

public class Lesson {
	public Date time;
	public String room;
	public LessonType type;

	public Lesson(Date time, String room, LessonType type) {
		this.time = time;
		this.room = room;
		this.type = type;
	}

	public String getDisplayText() {
		return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(time) + " | " + type + " | room " + room;
	}
}
