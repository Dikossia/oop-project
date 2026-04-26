package research;

import java.util.*;

public interface Researcher {

    /**
     * @return
     */
    int getHIndex();

    /**
     * @return
     */
    List<ResearchPaper> getPapers();

    /**
     * @param c
     */
    void printPapers(Comparator<ResearchPaper> c);

}
