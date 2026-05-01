package academic;

import java.util.Date;
import enums.ReportType;

public class Report {
	public ReportType type;
	public Date createdDate;
	public String data;

	public Report() {}

	public Report(ReportType type, String data) {
		this.type = type;
		this.data = data;
	}

	public Report (ReportType type, Date createdDate, String data) {
		this.type = type;
		this.createdDate = createdDate;
		this.data = data;
	}

	public void generate() {
		createdDate = new Date();
	}
}
