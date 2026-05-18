package research;

import exceptions.NotResearcherException;

import java.io.Serializable;
import java.util.*;

public class ResearchProject implements Serializable
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

    public void addParticipant(Researcher researcher) throws NotResearcherException
    {
        if (!participants.contains(researcher))
        {
            participants.add(researcher);
            researcher.getResearchProfile().addProject(this);
        }
    }

    public void deleteParticipant(Researcher researcher)
    {
        if (participants.contains(researcher))
        {
            participants.remove(researcher);
            researcher.getResearchProfile().removeProject(this);
        }
    }

    public boolean findParticipant(Researcher researcher)
    {
        return participants.contains(researcher);
    }

    public void addPaper(ResearchPaper paper)
    {
        papers.add(paper);
    }

    public void deletePaper(ResearchPaper paper)
    {
        papers.remove(paper);
    }

    public boolean findPaper(ResearchPaper paper)
    {
        return papers.contains(paper);
    }

    public String getTopic()
    {
        return topic;
    }

    public Set<Researcher> getParticipants()
    {
        return Set.copyOf(participants);
    }

    public Set<ResearchPaper> getPapers()
    {
        return Set.copyOf(papers);
    }

    @Override
    public String toString()
    {
        StringBuilder participantsList = new StringBuilder();
        for (Researcher researcher : participants)
        {
            participantsList.append("\n    ").append(researcher);
        }

        StringBuilder papersList = new StringBuilder();
        for (ResearchPaper paper : papers)
        {
            papersList.append("\n    ").append(paper);
        }

        return String.format("""
                        
             [%s]
                 Topic: %s
                 Participants:
                     %s
                 Papers:
                     %s
                             
             """, topic, topic, participantsList, papersList
        );
    }
}

