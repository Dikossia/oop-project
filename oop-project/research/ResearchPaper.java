package research;

import java.util.Date;

public class ResearchPaper {
	public String title;
	public String journal;
	public int pages;
	public int citations;
	public Date publishDate;
	public String doi;

	public ResearchPaper() {
	}

	public ResearchPaper(String title, int citations) {
		this.title = title;
		this.citations = citations;
		this.publishDate = new Date();
	}

	public String toString() {
		return title + " citations: " + citations;
	}
}
