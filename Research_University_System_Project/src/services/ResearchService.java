package services;

import enums.School;
import exceptions.LowHIndexException;
import exceptions.NotResearcherException;
import research.*;
import storage.DataStore;
import users.Student;
import users.User;

import java.util.Comparator;
import java.util.List;

public class ResearchService
{
    private final DataStore dataStore;

    public ResearchService(DataStore dataStore)
    {
        this.dataStore = dataStore;
    }

    public void assignSupervisor(Student student, Researcher supervisor) throws LowHIndexException
    {
        if (student.getYearOfStudy() != 4)
        {
            throw new IllegalArgumentException("Only 4th year students can have research supervisors");
        }
        if (supervisor.getResearchProfile().getHIndex() < 3)
        {
            throw new LowHIndexException("Supervisor must have h-index at least 3");
        }

        student.setResearchSupervisor(supervisor);
    }

    public void addProjectParticipant(User user, ResearchProject project) throws NotResearcherException
    {
        if (!(user instanceof Researcher researcher))
        {
            throw new NotResearcherException(user.getId() + " is NOT researcher.\n");
        }

        project.addParticipant(researcher);

        if (!dataStore.getResearchProjects().contains(project))
        {
            dataStore.getResearchProjects().add(project);
        }
    }

    public void addPaper(ResearchPaper paper)
    {
        if (!dataStore.getResearchPapers().contains(paper))
        {
            dataStore.getResearchPapers().add(paper);
        }
    }

    public void printAllPapers(Comparator<ResearchPaper> comparator)
    {
        dataStore.getResearchPapers()
                .stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    public List<Researcher> getResearchers()
    {
        return dataStore.getUsers().stream()
                .filter(u -> u instanceof Researcher)
                .map(u -> (Researcher) u)
                .toList();
    }

    public Researcher getMostCitedResearcher()
    {
        return getResearchers().stream()
                .max(Comparator.comparingInt(r -> r.getResearchProfile().getCitations()))
                .orElse(null);
    }

    public Researcher getMostCitedResearcherBySchool(School school)
    {
        return getResearchers().stream()
                .filter(r -> r.getResearchProfile().getSchool() == school)
                .max(Comparator.comparingInt(r -> r.getResearchProfile().getCitations()))
                .orElse(null);
    }

    public Researcher getMostCitedResearcherByYear(int year)
    {
        return getResearchers().stream()
                .max(Comparator.comparingInt(r -> r.getResearchProfile().getPapers().stream()
                        .filter(p -> p.getPublishDate().getYear() == year)
                        .mapToInt(ResearchPaper::getCitations)
                        .sum()))
                .orElse(null);
    }
}
