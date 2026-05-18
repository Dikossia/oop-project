package research;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper>
{
    private final String doi;
    private final String title;
    private final String journal;
    private final int pages;
    private final int citations;
    private final LocalDate publishDate;
    private final List<Researcher> authors;

    public ResearchPaper(String doi, String title, String journal, int pages, LocalDate publishDate)
    {
        this.doi = doi;
        this.title = title;
        this.journal = journal;
        this.pages = pages;
        this.citations = 0;
        this.publishDate = publishDate;
        this.authors = new ArrayList<>();
    }

    public String getDoi()
    {
        return doi;
    }

    public String getTitle()
    {
        return title;
    }

    public String getJournal()
    {
        return journal;
    }

    public int getPages()
    {
        return pages;
    }

    public int getCitations()
    {
        return citations;
    }

    public LocalDate getPublishDate()
    {
        return publishDate;
    }

    public List<Researcher> getAuthors()
    {
        return List.copyOf(authors);
    }

    public boolean addAuthor(Researcher author)
    {
        if (author == null || authors.contains(author))
        {
            return false;
        }

        authors.add(author);
        author.getResearchProfile().addPaper(this);

        return true;
    }

    public static final Comparator<ResearchPaper> BY_DATE =
            Comparator.comparing(ResearchPaper::getPublishDate);

    public static final Comparator<ResearchPaper> BY_CITATION =
            Comparator.comparing(ResearchPaper::getCitations);

    public static final Comparator<ResearchPaper> BY_LENGTH =
            Comparator.comparing(ResearchPaper::getPages);

    @Override
    public int compareTo(ResearchPaper o)
    {
        return Integer.compare(this.citations, o.citations);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchPaper that = (ResearchPaper) o;
        return doi.equals(that.doi);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(doi);
    }

    @Override
    public String toString()
    {
        return String.format("""
                            
            [%s]
                Title:       %s
                Journal:     %s
                Pages:       %d
                Citations:   %d
                Published:   %s
                Authors:     Authors
                            
            """, doi, title, journal, pages, citations, publishDate
        );
    }
}
