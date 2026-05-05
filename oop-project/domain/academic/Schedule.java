package academic;

import java.io.Serializable;
import users.Student;
import academic.ScheduleEntry;
import java.util.List;
import java.util.ArrayList;


public class Schedule implements Serializable {
	public Student student;
	public List<ScheduleEntry> scheduleEntries;
	//public TimeSlot timeSlot;		in progress..

	public Schedule(){
		scheduleEntries = new ArrayList<ScheduleEntry>();
	}

	public void addScheduleEntry(ScheduleEntry entry){
		scheduleEntries.add(entry);
	}

	public void removeScheduleEntry(ScheduleEntry entry){
		scheduleEntries.remove(entry);
	}

}
