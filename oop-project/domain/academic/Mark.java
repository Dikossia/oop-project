package academic;

import java.io.Serializable;

public class Mark implements Serializable {
	public double att1;
	public double att2;
	public double finalExam;

	public double getTotal() {
		return att1 + att2 + finalExam;
	}

	public String toString() {
		return "Total: " + getTotal();
	}
}
