package research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
	public String topic;
	public List<Researcher> participants;
	public List<ResearchPaper> papers;

	public ResearchProject() {
		participants = new ArrayList<Researcher>();
		papers = new ArrayList<ResearchPaper>();
	}

	public ResearchProject(String topic) {
		this();
		this.topic = topic;
	}

	public void addParticipant(Researcher r) {
		participants.add(r);
	}

	public void addPaper(ResearchPaper p) {
		papers.add(p);
	}
}
