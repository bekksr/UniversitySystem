package service;

import exception.LowHIndexException;
import exception.NotResearcherException;
import model.*;

import java.util.*;

public class ResearchService {

    public boolean assignSupervisor(Student s, Researcher r) {
        if (s == null || r == null) return false;
        s.supervisor = r;
        return true;
    }

    public boolean addParticipant(ResearchProject project, Researcher r) {
        if (project == null || r == null) return false;
        return project.addParticipant(r);
    }

    public void addPaper(ResearchProject project, ResearchPaper paper) {
        if (project != null && paper != null) {
            project.addPaper(paper);
        }
    }

    public List<ResearchPaper> getAllPapers(Comparator<ResearchPaper> c) {
        return new ArrayList<>();
    }

    public Researcher getTopCitedResearcher(int year) {
        return null;
    }
}
