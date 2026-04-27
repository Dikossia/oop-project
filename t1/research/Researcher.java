
package research;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
	int getHIndex();
	List<ResearchPaper> getPapers();
	void printPapers(Comparator<ResearchPaper> c);
}
