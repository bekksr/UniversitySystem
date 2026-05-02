package model;

import java.time.LocalDate;
import java.util.Objects;

public class ResearchPaper implements Comparable<ResearchPaper> {

    private String title;
    private int citations;
    private int pages;
    private LocalDate publicationDate;
    private String doi;

    public ResearchPaper(String title, int citations, int pages, LocalDate publicationDate, String doi) {
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public int getCitations() {
        return citations;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getDoi() {
        return doi;
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }

    @Override
    public String toString() {
        return title + " (" + citations + " citations)";
    }
}