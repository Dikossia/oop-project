package research;

import exceptions.NotResearcherException;
import users.User;

import java.io.Serializable;
import java.util.*;

public class ResearchProject implements Serializable {
    private final String topic;
    private final Set<Researcher> participants;
    private final Set<ResearchPaper> papers;

    public ResearchProject(String topic)
    {
        this.topic = topic;
        this.participants = new HashSet<>();
        this.papers = new HashSet<>();
    }

    public boolean addParticipant(User user) throws NotResearcherException
    {
        if (!(user instanceof Researcher researcher))
        {
            throw new NotResearcherException(user.getId() + " is NOT researcher");
        }

        if (!participants.contains(researcher))
        {
            participants.add(researcher);
            researcher.getResearchProfile().addProject(this);

            return true;
        }

        return false;
    }

    public boolean deleteParticipant(User user) throws NotResearcherException
    {
        if (!(user instanceof Researcher researcher))
        {
            throw new NotResearcherException(user.getId() + " is NOT researcher");
        }

        if (participants.contains(researcher))
        {
            participants.remove(researcher);
            researcher.getResearchProfile().removeProject(this);

            return true;
        }

        return false;
    }

    public boolean findParticipant(User user) throws NotResearcherException
    {
        if (!(user instanceof Researcher researcher))
        {
            throw new NotResearcherException(user.getId() + " is NOT researcher");
        }

        return participants.contains(researcher);
    }

    public boolean addPaper(ResearchPaper paper)
    {
        return papers.add(paper);
    }

    public boolean deletePaper(ResearchPaper paper)
    {
        return papers.remove(paper);
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

