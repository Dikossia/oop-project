package research;

import java.io.Serializable;
import java.util.Comparator;

public interface Researcher extends Serializable
{
    ResearchProfile getResearchProfile();

    default void printPapers(Comparator<ResearchPaper> comparator)
    {
        getResearchProfile().getPapers().stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
