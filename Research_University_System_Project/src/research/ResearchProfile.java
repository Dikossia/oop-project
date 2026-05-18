package research;

import enums.School;

import java.io.Serializable;
import java.util.*;

public class ResearchProfile implements Serializable
{
    private int hIndex;
    private School school;
    private final List<ResearchPaper> papers;
    private final List<ResearchProject> projects;

    public ResearchProfile(int hIndex, School school)
    {
        this.hIndex = hIndex;
        this.school = school;
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void addPaper(ResearchPaper paper)
    {
        if (paper != null && !papers.contains(paper)) {
            papers.add(paper);
            updateHIndex();
        }
    }

    void addProject(ResearchProject project) {
        projects.add(project);
    }

    void removeProject(ResearchProject project) {
        projects.remove(project);
    }

    public int getHIndex()
    {
        return hIndex;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<ResearchPaper> getPapers()
    {
        return List.copyOf(papers);
    }

    public List<ResearchProject> getProjects()
    {
        return List.copyOf(projects);
    }

    public int getCitations() {
        int citations = 0;
        for (ResearchPaper paper : papers) {
            citations += paper.getCitations();
        }

        return citations;
    }

    private void updateHIndex()
    {
        List<Integer> citations = papers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Comparator.reverseOrder())
                .toList();

        int h = 0;
        for (int i = 0; i < citations.size(); i++)
        {
            if (citations.get(i) >= i + 1)
            {
                h++;
            }
            else
            {
                break;
            }
        }

        hIndex = h;
    }

    @Override
    public String toString()
    {
        StringBuilder papersList = new StringBuilder();
        for (ResearchPaper paper : papers)
            papersList.append("\n    ").append(paper);

        StringBuilder projectsList = new StringBuilder();
        for (ResearchProject project : projects)
            projectsList.append("\n    ").append(project);

        return String.format("""
                        
        [Research Profile]
            School: %s
            H-Index: %d
            Citations: %d
            Papers:
                %s
            Projects:
                %s
                        
        """, school, hIndex, getCitations(), papersList, projectsList
        );
    }
}
