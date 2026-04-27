package research;

import java.util.*;

public interface Researcher
{
    int getHIndex();

    void updateHIndex();

    boolean addPaper(ResearchPaper paper);

    List<ResearchPaper> getPapers();

    void printPapers(Comparator<ResearchPaper> c);
}
