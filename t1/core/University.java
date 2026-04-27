package core;

import java.io.*;
import java.util.*;

import users.User;
import academic.Course;
import research.ResearchProject;

public class University implements Serializable {

	private static final String FILE = "data.ser";
	private static University instance;

	public List<User> users = new ArrayList<>();
	public List<Course> courses = new ArrayList<>();
	public List<ResearchProject> projects = new ArrayList<>();

	public static University getInstance() {
		if(instance == null) {
			instance = load();
			if(instance == null) instance = new University();
		}
		return instance;
	}

	public static University load() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
			return (University) ois.readObject();
		}
		catch(Exception e) {
			return null;
		}
	}

	public void save() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
			oos.writeObject(this);
		}
		catch(Exception e) {
			System.out.println("Save error");
		}
	}
}
