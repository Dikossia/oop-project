package academic;

import users.Employee;
import enums.RequestStatus;
import enums.RequestType;

public class Request {
	public int id;
	public Employee sender;
	public RequestType type;
	public RequestStatus status;
	public String description;

	public void approve() {
		status = RequestStatus.APPROVED;
	}

	public void reject() {
		status = RequestStatus.REJECTED;
	}
}
