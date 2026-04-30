package core;

import java.io.*;
import java.util.*;

import academic.Course;
import academic.Lesson;
import core.University;
import enums.LessonType;
import users.*;
import academic.Schedule;
import academic.ScheduleEntry;
import academic.Room;

public class DataLoader {

	private static final String COURSE_FILE = "./data/courses.txt";
	private static final String SCHEDULE_FILE = "./data/schedule.txt";
	private static final String USERS_FILE = "./data/users.txt";
	private static final String SCHEDULE_FILE_2 = "./data/schedule2.txt";

	public static List<Course> loadCourses(String fileName) {

		List<Course> courses = new ArrayList<Course>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line;

			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");

				if (parts.length != 3) continue;

				String name = parts[0].trim();
				String code = parts[1].trim();
				int credits = Integer.parseInt(parts[2].trim());

				Course c = new Course(name, code, credits);
				c.isOpen = true;

				courses.add(c);
			}

			br.close();

		} catch (Exception e) {
			System.out.println("Cannot load courses");
		}

		return courses;
	}

	public static void saveCourses(List<Course> courses) {

		try {

			PrintWriter pw = new PrintWriter(new FileWriter(COURSE_FILE));

			for (Course c : courses) {
				pw.println(c.name + "," + c.code + "," + c.credits);
			}

			pw.close();

		} catch (Exception e) {
			System.out.println("Cannot save courses");
		}
	}

	public static void loadSchedule2(){
		University uni = University.getInstance();
		//i want so that each Student will get inited its own schedule at this step)
		try {
			BufferedReader br = new BufferedReader(new FileReader(SCHEDULE_FILE_2));

			String line;

			while ((line = br.readLine()) != null){

				line.trim();
				if(line.isEmpty()) continue;

				String[] parts = line.split("\\|");
				if(parts.length % 3 != 1)continue;

				int sId = Integer.parseInt(parts[0].trim());
				Student s = uni.findStudentById(sId);
				if(s == null)continue;
				if(s.schedule == null){
					s.schedule = new Schedule();
				}

				for(int i = 1;  i < parts.length; i ++){
					String[] data = parts[i].split(",");
					String courseName = data[0].trim();
					String teacherName = data[1].trim();
					Room room = new Room(data[2].trim());

					ScheduleEntry entry = new ScheduleEntry(
						courseName, teacherName, room);

					s.schedule.addScheduleEntry(entry);
				}
			}
			br.close();

		} catch (Exception e) {
			System.out.println("No schedule file found");
		}


	}

	public static void loadSchedule() {

		University uni = University.getInstance();

		try {

			BufferedReader br = new BufferedReader(new FileReader(SCHEDULE_FILE));

			String line;

			while ((line = br.readLine()) != null) {

				line = line.trim();

				if (line.length() == 0) continue;

				int value = Integer.parseInt(line);

				int year = (value >> 26) & 63;
				int month = (value >> 22) & 15;
				int day = (value >> 17) & 31;
				int hour = (value >> 12) & 31;
				int minute = (value >> 6) & 63;
				int courseId = (value >> 3) & 7;
				int typeId = value & 7;

				if (courseId >= uni.courses.size()) continue;

				Course course = uni.courses.get(courseId);

				Calendar cal = Calendar.getInstance();

				cal.set(
					2000 + year,
					month - 1,
					day,
					hour,
					minute,
					0
				);

				LessonType type = LessonType.LECTURE;

				if (typeId == 1) {
					type = LessonType.PRACTICE;
				}

				Lesson lesson =
					new Lesson(
						cal.getTime(),
						"AUTO",
						type
					);

				course.addLesson(lesson);
			}

			br.close();

		} catch (Exception e) {
			System.out.println("No schedule file found");
		}
	}

	public static void saveSchedule() {

		University uni = University.getInstance();

		try {

			PrintWriter pw =
				new PrintWriter(
					new FileWriter(SCHEDULE_FILE)
				);

			for (int i = 0; i < uni.courses.size(); i++) {

				Course c = uni.courses.get(i);

				for (Lesson lesson : c.lessons) {

					Calendar cal = Calendar.getInstance();
					cal.setTime(lesson.time);

					int year =
						cal.get(Calendar.YEAR) - 2000;

					int month =
						cal.get(Calendar.MONTH) + 1;

					int day =
						cal.get(Calendar.DAY_OF_MONTH);

					int hour =
						cal.get(Calendar.HOUR_OF_DAY);

					int minute =
						cal.get(Calendar.MINUTE);

					int type = 0;

					if (lesson.type ==
						LessonType.PRACTICE)
					{
						type = 1;
					}

					int value = 0;

					value |= (year << 26);
					value |= (month << 22);
					value |= (day << 17);
					value |= (hour << 12);
					value |= (minute << 6);
					value |= (i << 3);
					value |= type;

					pw.println(value);
				}
			}

			pw.close();

		} catch (Exception e) {
			System.out.println("Cannot save schedule");
		}
	}

	public static List<User> loadUsers(String file) {

		List<User> users = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;

			while ((line = br.readLine()) != null) {

				String[] p = line.split("\\|");

				switch (p[0]) {

					case "ADMIN":
						Admin a = new Admin();
						a.id = Integer.parseInt(p[1]);
						a.username = p[2];
						a.password = p[3];
						a.email = p[4];
						users.add(a);
						break;

					case "STUDENT":
						Student s = new Student(
							Integer.parseInt(p[1]),
							p[2],
							p[3],
							p[4],
							Double.parseDouble(p[5]),
							Integer.parseInt(p[6]),
							Integer.parseInt(p[7]),
							Integer.parseInt(p[8])
						);
						users.add(s);
						break;

					case "TEACHER":
						Teacher t = new Teacher();
						t.id = Integer.parseInt(p[1]);
						t.username = p[2];
						t.password = p[3];
						t.email = p[4];
						t.title = enums.TeacherType.valueOf(p[5]);
						users.add(t);
						break;

					case "MANAGER":
						Manager m = new Manager();
						m.id = Integer.parseInt(p[1]);
						m.username = p[2];
						m.password = p[3];
						m.email = p[4];
						m.type = enums.ManagerType.valueOf(p[5]);
						users.add(m);
						break;
				}
			}

		} catch (Exception e) {
			System.out.println("Cannot load users");
		}

		return users;
	}

	public static void saveUsers(List<User> users, String file) {

		try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {

			for (User u : users) {

				if (u instanceof Admin) {
					pw.println("ADMIN|" + u.id + "|" + u.username + "|" +
								u.password + "|" + u.email);
				}

				else if (u instanceof Student s) {
					pw.println("STUDENT|" + s.id + "|" + s.username + "|" +
								s.password + "|" + s.email + "|" + s.gpa +
								"|" + s.year + "|" + s.credits + "|" +
								s.failedCourses);
				}

				else if (u instanceof Teacher t) {
					pw.println("TEACHER|" + t.id + "|" + t.username + "|" +
								t.password + "|" + t.email + "|" + t.title);
				}

				else if (u instanceof Manager m) {
					pw.println("MANAGER|" + m.id + "|" + m.username + "|" +
								m.password + "|" + m.email + "|" + m.type);
				}
			}

		} catch (Exception e) {
			System.out.println("Cannot save users");
		}
	}

	public static void saveLogs(List<Log> logs, String file){


	}

	public static void assignTeacherToAll(Teacher t) {

		University uni = University.getInstance();

		for (Course c : uni.courses) {

			if (!c.instructors.contains(t)) {
				c.addInstructor(t);
			}

			if (!t.courses.contains(c)) {
				t.courses.add(c);
			}
		}
	}
}
