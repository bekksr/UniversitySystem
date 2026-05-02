package model;

import enums.ProjectStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> papers;
    private Date startDate;
    private Date endDate;
    private ProjectStatus status;

    public ResearchProject(String topic, Date startDate, Date endDate) {
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ProjectStatus.PLANNED;
        this.participants = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    public boolean addParticipant(Researcher r) {
        if (r == null || participants.contains(r)) return false;
        participants.add(r);
        return true;
    }

    public boolean removeParticipant(Researcher r) {
        return participants.remove(r);
    }

    public boolean addPaper(ResearchPaper paper) {
        if (paper == null || papers.contains(paper)) return false;
        papers.add(paper);
        return true;
    }

    public List<ResearchPaper> getPapers() {
        return new ArrayList<>(papers);
    }

    public List<Researcher> getParticipants() {
        return new ArrayList<>(participants);
    }

    public String getProjectInfo() {
        return "ResearchProject{topic='" + topic + "', status=" + status +
               ", participants=" + participants.size() +
               ", papers=" + papers.size() + "}";
    }

    public String getTopic() { return topic; }
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
}
