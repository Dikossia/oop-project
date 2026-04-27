package users;

import java.io.*;
import java.util.*;
import java.util.Date;


public abstract class Employee extends User {
	public double salary;
	public Date hireDate;

	public Employee() {
	}

	public Employee(int id, String username, String password, String email, double salary, Date hireDate) {
		super(id, username, password, email);
		this.salary = salary;
		this.hireDate = hireDate;
	}
}
