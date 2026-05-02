package service;

import model.ResearchPaper;

import java.util.Comparator;

public class ResearchPaperComparator {

    public static Comparator<ResearchPaper> byCitations() {
        return (p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations());
    }

    public static Comparator<ResearchPaper> byDate() {
        return (p1, p2) -> p2.getPublicationDate().compareTo(p1.getPublicationDate());
    }

    public static Comparator<ResearchPaper> byPages() {
        return (p1, p2) -> Integer.compare(p2.getPages(), p1.getPages());
    }
}