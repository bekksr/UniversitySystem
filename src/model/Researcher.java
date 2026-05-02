package model;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
	int getHIndex();

    List<ResearchPaper> printPapers(Comparator<ResearchPaper> comparator);
}
