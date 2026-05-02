package model;

import enums.ProjectStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject {

    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> papers;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;

    public ResearchProject(String topic, LocalDate startDate, LocalDate endDate, ProjectStatus status) {
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.participants = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    public boolean addParticipant(Researcher researcher) {
        if (researcher == null || participants.contains(researcher)) {
            return false;
        }
        participants.add(researcher);
        return true;
    }

    public boolean removeParticipant(Researcher researcher) {
        return participants.remove(researcher);
    }

    public boolean addPaper(ResearchPaper paper) {
        if (paper == null || papers.contains(paper)) {
            return false;
        }
        papers.add(paper);
        return true;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public String getProjectInfo() {
        return "Topic: " + topic +
                ", Status: " + status +
                ", Participants: " + participants.size() +
                ", Papers: " + papers.size();
    }

    public boolean isActive() {
        return status == ProjectStatus.ACTIVE;
    }
}