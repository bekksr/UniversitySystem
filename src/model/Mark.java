package model;

public class Mark {
    public double att1;
    public double att2;
    public double finalExam;

    private Student student;
    private Course course;

    public Mark(double att1, double att2, double finalExam) {
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
    }

    public Mark(double att1, double att2, double finalExam, Student student, Course course) {
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
        this.student = student;
        this.course = course;
    }

    public double calculateGrade() {
        return att1 * 0.3 + att2 * 0.3 + finalExam * 0.4;
    }

    public String getLetterGrade() {
        double total = calculateGrade();
        if (total >= 95) return "A+";
        if (total >= 90) return "A";
        if (total >= 85) return "A-";
        if (total >= 80) return "B+";
        if (total >= 75) return "B";
        if (total >= 70) return "B-";
        if (total >= 65) return "C+";
        if (total >= 60) return "C";
        if (total >= 55) return "C-";
        if (total >= 50) return "D+";
        if (total >= 45) return "D";
        return "F";
    }

    public double getGpaPoints() {
        double total = calculateGrade();
        if (total >= 95) return 4.0;
        if (total >= 90) return 3.67;
        if (total >= 85) return 3.33;
        if (total >= 80) return 3.0;
        if (total >= 75) return 2.67;
        if (total >= 70) return 2.33;
        if (total >= 65) return 2.0;
        if (total >= 60) return 1.67;
        if (total >= 55) return 1.33;
        if (total >= 50) return 1.0;
        if (total >= 45) return 0.67;
        return 0.0;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Mark{att1=" + att1 + ", att2=" + att2 + ", final=" + finalExam +
               ", total=" + String.format("%.1f", calculateGrade()) +
               ", grade=" + getLetterGrade() + "}";
    }
}
