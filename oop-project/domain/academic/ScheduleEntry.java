package academic;

import java.io.Serializable;
import academic.Course;

public class ScheduleEntry implements Serializable {
	public String courseName;
	public String teacherName;
	public Room room;

	public ScheduleEntry(String courseName, String teacherName, Room room){
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.room = room;
	}

	public void setTeacher(String teacherName){
		this.teacherName = teacherName;
	}

	public void setCourse(String courseName){
		this.courseName = courseName;
	}

	public void setRoom(Room room){
		this.room = room;
	}

}
