package research;

import exceptions.NonResearcherException;
import users.Employee;

import java.util.*;

public class ResearchProject
{
    private final String topic;
    private final Set<Researcher> participants;
    private final Set<ResearchPaper> papers;


    public ResearchProject(String topic)
    {
        this.topic = topic;
        this.participants = new HashSet<>();
        this.papers = new HashSet<>();
    }

    public boolean addParticipant(Employee researcher) throws NonResearcherException
    {
        if (!(researcher instanceof Researcher))
        {
            throw new NonResearcherException(researcher.id + "is NOT researcher");
        }

        if (participants.add((Researcher) researcher))
        {
            /* Delete after debugging */
            System.out.println("The researcher is NOW participating in the project.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The researcher is ALREADY participating in the project.");
        /* Delete after debugging */
        return false;
    }

    public boolean deleteParticipant(Employee researcher) throws NonResearcherException
    {
        if (!(researcher instanceof Researcher))
        {
            throw new NonResearcherException(researcher.id + "is NOT researcher");
        }

        if (participants.remove((Researcher) researcher))
        {
            /* Delete after debugging */
            System.out.println("The researcher is REMOVED from the project.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The researcher is NOT participating in the project.");
        /* Delete after debugging */
        return false;
    }

    public boolean findParticipant(Employee researcher) throws NonResearcherException
    {
        if (participants.contains((Researcher) researcher))
        {
            /* Delete after debugging */
            System.out.println("The researcher is participating in the project.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The researcher is NOT participating in the project.");
        /* Delete after debugging */
        return false;
    }

    public boolean addPaper(ResearchPaper researchPaper)
    {
        if (papers.add(researchPaper))
        {
            /* Delete after debugging */
            System.out.println("The paper is NOW in the project's library.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The paper is ALREADY in the project's library.");
        /* Delete after debugging */
        return false;
    }

    public boolean deletePaper(ResearchPaper researchPaper)
    {
        if (papers.remove(researchPaper))
        {
            /* Delete after debugging */
            System.out.println("The paper is REMOVED from the project's library.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The paper is NOT participating in the project's library.");
        /* Delete after debugging */
        return false;
    }

    public boolean findPaper(ResearchPaper researchPaper)
    {
        if (papers.contains(researchPaper))
        {
            /* Delete after debugging */
            System.out.println("The paper is in the project's library.");
            /* Delete after debugging */
            return true;
        }

        /* Delete after debugging */
        System.out.println("The paper is not in the project's library.");
        /* Delete after debugging */
        return false;
    }

    public String getTopic()
    {
        return topic;
    }

    public Set<Researcher> getParticipants()
    {
        return Set.copyOf(participants);
    }

    public List<ResearchPaper> getPapers()
    {
        return List.copyOf(papers);
    }
}
