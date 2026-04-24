
import java.io.*;
import java.util.*;

/**
 * 
 */
public interface Researcher {


    /**
     * @return
     */
    public int getHIndex();

    /**
     * @return
     */
    public List<ResearchPaper> getPapers();

    /**
     * @param c
     */
    public void printPapers(Comparator c);

}