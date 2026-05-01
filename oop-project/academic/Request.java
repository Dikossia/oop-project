package academic;

import users.Employee;
import enums.RequestStatus;
import enums.RequestType;

public class Request {
	public int id;
	public RequestType type;
	public RequestStatus status;
	public String description;

	public Request() {}

	public Request (int id, RequestType type,
			RequestStatus status, String description) {
		this.id = id;
		this.type = type;
		this.status = status;
		this.description = description;
	}

	public void approve() {
		status = RequestStatus.APPROVED;
	}

	public void reject() {
		status = RequestStatus.REJECTED;
	}
}
