package model;

import java.util.Date;
import java.util.Objects;

public class ResearchPaper implements Comparable<ResearchPaper> {
    private String title;
    private int citations;
    private int pages;
    private Date publicationDate;
    private String doi;

    public ResearchPaper(String title, int citations, int pages, Date publicationDate, String doi) {
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    @Override
    public int compareTo(ResearchPaper paper) {
        return Integer.compare(this.citations, paper.citations);
    }

    @Override
    public String toString() {
        return "ResearchPaper{title='" + title + "', citations=" + citations +
               ", pages=" + pages + ", doi='" + doi + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }

    public String getTitle() { return title; }
    public int getCitations() { return citations; }
    public int getPages() { return pages; }
    public Date getPublicationDate() { return publicationDate; }
    public String getDoi() { return doi; }
    public void setCitations(int citations) { this.citations = citations; }
}
