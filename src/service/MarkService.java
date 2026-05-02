package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class MarkService {

    public boolean putMark(Teacher t, Student s, Course c, Mark m) {
        if (t == null || s == null || c == null || m == null) return false;

        if (!c.getTeachers().contains(t)) {
            System.out.println("Teacher " + t.name + " does not teach " + c.name);
            return false;
        }

        if (!c.getStudents().contains(s)) {
            System.out.println("Student " + s.name + " is not enrolled in " + c.name);
            return false;
        }

        if (m.att1 < 0 || m.att1 > 100 || m.att2 < 0 || m.att2 > 100 ||
            m.finalExam < 0 || m.finalExam > 100) {
            System.out.println("Invalid mark values. Must be between 0 and 100.");
            return false;
        }

        m.setStudent(s);
        m.setCourse(c);
        s.addMark(m);
        System.out.println("Mark set for " + s.name + " in " + c.name +
                         ": " + m.getLetterGrade() + " (" +
                         String.format("%.1f", m.calculateGrade()) + ")");
        return true;
    }

    public List<Mark> viewMarks(Student s) {
        if (s == null) return new ArrayList<>();
        return s.getMarks();
    }

    public double calculateGrade(Mark m) {
        if (m == null) return 0.0;
        return m.calculateGrade();
    }

    public String generateMarkReport(Course c) {
        if (c == null) return "No course specified.";

        StringBuilder sb = new StringBuilder();
        sb.append("========== MARK REPORT ==========\n");
        sb.append("Course: ").append(c.name).append(" (").append(c.credits).append(" credits)\n");
        sb.append("Teachers: ");
        List<Teacher> teachers = c.getTeachers();
        if (teachers.isEmpty()) {
            sb.append("None assigned");
        } else {
            for (int i = 0; i < teachers.size(); i++) {
                sb.append(teachers.get(i).name);
                if (i < teachers.size() - 1) sb.append(", ");
            }
        }
        sb.append("\n");
        sb.append("---------------------------------\n");
        sb.append(String.format("%-20s %-6s %-6s %-6s %-8s %-6s\n",
                "Student", "Att1", "Att2", "Final", "Total", "Grade"));
        sb.append("---------------------------------\n");

        List<Student> students = c.getStudents();
        int passCount = 0;
        int failCount = 0;
        double totalGrade = 0;

        for (Student s : students) {
            List<Mark> marks = s.getMarks();
            Mark courseMark = null;
            for (Mark m : marks) {
                if (c.equals(m.getCourse())) {
                    courseMark = m;
                    break;
                }
            }

            if (courseMark != null) {
                double grade = courseMark.calculateGrade();
                totalGrade += grade;
                if (grade >= 50) passCount++;
                else failCount++;

                sb.append(String.format("%-20s %-6.1f %-6.1f %-6.1f %-8.1f %-6s\n",
                        s.name, courseMark.att1, courseMark.att2, courseMark.finalExam,
                        grade, courseMark.getLetterGrade()));
            } else {
                sb.append(String.format("%-20s %-6s %-6s %-6s %-8s %-6s\n",
                        s.name, "-", "-", "-", "-", "N/A"));
            }
        }

        sb.append("---------------------------------\n");
        int totalWithMarks = passCount + failCount;
        if (totalWithMarks > 0) {
            sb.append("Average: ").append(String.format("%.1f", totalGrade / totalWithMarks)).append("\n");
            sb.append("Passed: ").append(passCount).append(" | Failed: ").append(failCount).append("\n");
        }
        sb.append("Total students: ").append(students.size()).append("\n");
        sb.append("=================================\n");

        return sb.toString();
    }
}
