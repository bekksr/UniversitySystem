package service;

import model.Student;
import model.Course;

public class ReportGenerator {

    public String generateStudentReport(Student s) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Student Report ===\n");
        sb.append("Name: ").append(s.getName() != null ? s.getName() : "Unknown").append("\n");
        sb.append("Email: ").append(s.getEmail() != null ? s.getEmail() : "Unknown").append("\n");
        sb.append("GPA: ").append(s.getGpa()).append("\n");
        sb.append("======================\n");
        return sb.toString();
    }

    public String generateCourseReport(Course c) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Course Report ===\n");
        sb.append("Course Name: ").append(c.getName() != null ? c.getName() : "Unknown").append("\n");
        sb.append("Credits: ").append(c.getCredits()).append("\n");
        sb.append("Number of Students: ").append(c.getStudents() != null ? c.getStudents().size() : 0).append("\n");
        sb.append("Number of Teachers: ").append(c.getTeachers() != null ? c.getTeachers().size() : 0).append("\n");
        sb.append("=====================\n");
        return sb.toString();
    }

    public String generateMarksStatistics(Course c) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Marks Statistics for ").append(c.getName() != null ? c.getName() : "Unknown").append(" ===\n");
        sb.append("Total Students: ").append(c.getStudents() != null ? c.getStudents().size() : 0).append("\n");
        sb.append("Average Grade: N/A (Needs MarkService integration)\n");
        sb.append("===========================\n");
        return sb.toString();
    }
}
