package service;

import exception.LowHIndexException;
import exception.NotResearcherException;
import model.ResearchPaper;
import model.ResearchProject;
import model.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchService {

    public boolean assignSupervisor(Researcher student, Researcher supervisor) throws LowHIndexException {
        if (supervisor.getHIndex() < 3) {
            throw new LowHIndexException("Supervisor has too low h-index");
        }
        return true;
    }

    public boolean addParticipant(ResearchProject project, Researcher researcher)
            throws NotResearcherException {

        if (researcher == null) {
            throw new NotResearcherException("User is not a researcher");
        }

        return project.addParticipant(researcher);
    }

    public boolean addPaper(ResearchProject project, ResearchPaper paper) {
        return project.addPaper(paper);
    }

    public List<ResearchPaper> getAllPapers(List<ResearchProject> projects,
                                            Comparator<ResearchPaper> comparator) {

        List<ResearchPaper> allPapers = new ArrayList<>();

        for (ResearchProject project : projects) {
            allPapers.addAll(project.getPapers());
        }

        allPapers.sort(comparator);

        return allPapers;
    }

    public Researcher getTopCitedResearcher(List<Researcher> researchers) {
        if (researchers.isEmpty()) return null;

        Researcher top = researchers.get(0);

        for (Researcher r : researchers) {
            if (r.getHIndex() > top.getHIndex()) {
                top = r;
            }
        }

        return top;
    }
}