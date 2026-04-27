package academic;

import java.util.Date;
import enums.ReportType;

public class Report {
	public ReportType type;
	public Date createdDate;
	public String data;

	public void generate() {
		createdDate = new Date();
	}
}
