package model;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    void printPapers(Comparator<ResearchPaper> c);
    int getHIndex();
}
