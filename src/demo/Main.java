package demo;

import enums.ProjectStatus;
import model.ResearchPaper;
import model.ResearchProject;
import model.Researcher;
import service.ResearchPaperComparator;
import service.ResearchService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ResearchService researchService = new ResearchService();

        Researcher r1 = new Researcher() {
            @Override
            public int getHIndex() {
                return 10;
            }

            @Override
            public List<ResearchPaper> printPapers(java.util.Comparator<ResearchPaper> comparator) {
                return null;
            }
        };

        Researcher r2 = new Researcher() {
            @Override
            public int getHIndex() {
                return 5;
            }

            @Override
            public List<ResearchPaper> printPapers(java.util.Comparator<ResearchPaper> comparator) {
                return null;
            }
        };

        ResearchPaper p1 = new ResearchPaper(
                "AI Research", 120, 10,
                LocalDate.of(2023, 5, 10), "doi1"
        );

        ResearchPaper p2 = new ResearchPaper(
                "ML Study", 80, 8,
                LocalDate.of(2024, 2, 15), "doi2"
        );

        ResearchProject project = new ResearchProject(
                "AI Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                ProjectStatus.ACTIVE
        );

        try {
            researchService.addParticipant(project, r1);
            researchService.addParticipant(project, r2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        researchService.addPaper(project, p1);
        researchService.addPaper(project, p2);

        List<ResearchPaper> sorted = researchService.getAllPapers(
                Arrays.asList(project),
                ResearchPaperComparator.byCitations()
        );

        System.out.println("Sorted by citations:");
        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }

        Researcher top = researchService.getTopCitedResearcher(Arrays.asList(r1, r2));

        System.out.println("Top researcher h-index: " + top.getHIndex());
    }
}