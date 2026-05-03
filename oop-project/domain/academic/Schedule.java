package academic;

import users.Student;
import academic.ScheduleEntry;
import java.util.List;
import java.util.ArrayList;


public class Schedule {
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
