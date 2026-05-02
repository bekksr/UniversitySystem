package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends User implements Researcher {
    public double gpa;
    public int year;
    public Researcher supervisor;

    private List<Course> courses;
    private List<Mark> marks;
    private List<ResearchPaper> papers;

    public Student(int id, String name, String email, String password, int year) {
        super(id, name, email, password);
        this.year = year;
        this.gpa = 0.0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    public List<Course> viewCourses() {
        return new ArrayList<>(courses);
    }

    public String viewTranscript() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Transcript for ").append(name).append(" ===\n");
        sb.append("Year: ").append(year).append(" | GPA: ")
          .append(String.format("%.2f", gpa)).append("\n");
        sb.append("-----------------------------------\n");

        for (Mark mark : marks) {
            Course c = mark.getCourse();
            String courseName = (c != null) ? c.name : "Unknown";
            sb.append(courseName).append(": ")
              .append(String.format("%.1f", mark.calculateGrade()))
              .append(" (").append(mark.getLetterGrade()).append(")\n");
        }

        if (marks.isEmpty()) {
            sb.append("No marks yet.\n");
        }
        sb.append("===================================\n");
        return sb.toString();
    }

    public boolean rateTeacher(Teacher t, int rating) {
        if (t == null || rating < 1 || rating > 5) {
            return false;
        }
        System.out.println(name + " rated " + t.name + ": " + rating + "/5");
        return true;
    }

    public boolean canRetake() {
        for (Mark m : marks) {
            if (m.calculateGrade() < 50) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(Course c) {
        if (!courses.contains(c)) {
            courses.add(c);
        }
    }

    public void removeCourse(Course c) {
        courses.remove(c);
    }

    public void addMark(Mark mark) {
        marks.add(mark);
        recalculateGpa();
    }

    public List<Mark> getMarks() {
        return new ArrayList<>(marks);
    }

    public int getTotalCredits() {
        int total = 0;
        for (Course c : courses) {
            total += c.credits;
        }
        return total;
    }

    private void recalculateGpa() {
        if (marks.isEmpty()) {
            gpa = 0.0;
            return;
        }
        double totalPoints = 0;
        int count = 0;
        for (Mark m : marks) {
            totalPoints += m.getGpaPoints();
            count++;
        }
        gpa = totalPoints / count;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort(c);
        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }
    }

    @Override
    public int getHIndex() {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));
        int h = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getCitations() >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }
}
