package service;

import model.*;
import java.util.List;

public class ReportGenerator {

    public String generateStudentReport(Student s) {
        if (s == null) return "No student specified.";
        return s.viewTranscript();
    }

    public String generateCourseReport(Course c) {
        if (c == null) return "No course specified.";
        MarkService markService = new MarkService();
        return markService.generateMarkReport(c);
    }

    public String generateMarksStatistics(Course c) {
        if (c == null) return "No course specified.";

        List<Student> students = c.getStudents();
        double totalGrade = 0;
        double maxGrade = 0;
        double minGrade = 100;
        int count = 0;

        for (Student s : students) {
            for (Mark m : s.getMarks()) {
                if (c.equals(m.getCourse())) {
                    double grade = m.calculateGrade();
                    totalGrade += grade;
                    maxGrade = Math.max(maxGrade, grade);
                    minGrade = Math.min(minGrade, grade);
                    count++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Statistics for ").append(c.name).append(" ===\n");
        if (count > 0) {
            sb.append("Students graded: ").append(count).append("\n");
            sb.append("Average: ").append(String.format("%.1f", totalGrade / count)).append("\n");
            sb.append("Max: ").append(String.format("%.1f", maxGrade)).append("\n");
            sb.append("Min: ").append(String.format("%.1f", minGrade)).append("\n");
        } else {
            sb.append("No marks available.\n");
        }
        return sb.toString();
    }
}
