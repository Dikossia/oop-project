package users;

import java.util.*;
import java.util.Date;

import academic.Request;
import enums.RequestType;
import enums.RequestStatus;


public abstract class Employee extends User {
	public double salary;
	public Date hireDate;
	protected static List<Request> requests = new ArrayList<Request>();

	public Employee() {
			requests = new ArrayList<Request>();
		}

	public Employee(int id, String username, String password, String email, double salary, Date hireDate) {
		super(id, username, password, email);
		this.salary = salary;
		this.hireDate = hireDate;
		requests = new ArrayList<Request>();
	}
	public static List<Request> getRequestInstance(){
		return requests;
	}

	public void addRequest(){
		Scanner sc = new Scanner(System.in);
		Request r = new Request();
		System.out.println("Choose request type:");
		System.out.println("1. COURSE_OPEN");
		System.out.println("2. GRADE_CHANGE");
		System.out.println("3. SCHEDULE_CHANGE");
		System.out.println("4. OTHER");

		int choice = sc.nextInt();
		sc.nextLine();

		switch(choice) {
			case 1:
				r.type = RequestType.COURSE_OPEN;
				break;
			case 2:
				r.type = RequestType.GRADE_CHANGE;
				break;
			case 3:
				r.type = RequestType.SCHEDULE_CHANGE;
				break;
			default:
				r.type = RequestType.OTHER;
		}

		r.senderId = this.id;
		r.status = RequestStatus.PENDING;
		System.out.println("Description: ");
		r.description = sc.nextLine();

		requests.add(r);
		System.out.println("Request sent");
		sc.close();
	}

	public void addRequest(Request request){
		requests.add(request);
	}
}
