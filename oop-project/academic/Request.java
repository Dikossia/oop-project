package academic;

import users.Employee;
import enums.RequestStatus;
import enums.RequestType;
import java.util.concurrent.atomic.AtomicLong;

public class Request {
	private static AtomicLong requestIdCounter = new AtomicLong(0);
	public int senderId;
	public RequestType type;
	public RequestStatus status;
	public String description;
	public long requestId;

	public Request() {
		this.requestId = requestIdCounter.getAndIncrement();
		}

	public Request (int id, RequestType type,
			RequestStatus status, String description) {
		this.requestId = requestIdCounter.getAndIncrement();
		this.senderId = id;
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
