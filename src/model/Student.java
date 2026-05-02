package model;
import enums.StudentDegree;
import exception.LowHIndexException;
import observer.Observer;
 
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Observer {
    private final String studentId;
    private final StudentDegree degree;  
    private int                 year;    
    private double              gpa;
    private Researcher          supervisor;   
 
    private final List<Course>  enrolledCourses = new ArrayList<>();
    private final List<Mark>    transcript      = new ArrayList<>();
    private final List<String>  inbox           = new ArrayList<>();
 
    private StudentResearchProfile researchProfile; 
 
    public Student(String firstName, String lastName, String email, String password,
                   String studentId, int year) {
        super(firstName, lastName, email, password);
        this.studentId = studentId;
        this.degree    = StudentDegree.BACHELOR;
        this.year      = year;
    }
 
    // supervisor
 
    public void setSupervisor(Researcher supervisor) throws LowHIndexException {
        if (year != 4) {
            System.out.println("Only 4th-year students need a supervisor.");
            return;
        }
        int h = supervisor.calculateHIndex();
        if (h < 3) throw new LowHIndexException(h, 3);
        this.supervisor = supervisor;
        System.out.println(getFullName() + "'s supervisor set to: " + supervisor.getFullName());
    }
 
    public Researcher getSupervisor() { return supervisor; }
 
    // researcher opt in
 
    public boolean isResearcher() { return researchProfile != null; }
 
    // make a student a researcher
    public StudentResearchProfile enableResearch() {
        if (researchProfile == null) {
            researchProfile = new StudentResearchProfile(this);
        }
        return researchProfile;
    }
 
    public StudentResearchProfile getResearchProfile() { return researchProfile; }
 
    // course transcript
 
    public List<Course> viewCourses() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        enrolledCourses.forEach(c -> System.out.println("  " + c));
        return new ArrayList<>(enrolledCourses);
    }
 
    public List<Mark> viewTranscript() {
        System.out.println("=== Transcript of " + getFullName() + " (GPA=" + String.format("%.2f", gpa) + ") ===");
        transcript.forEach(m -> System.out.println("  " + m));
        return new ArrayList<>(transcript);
    }
 
    public void rateTeacher(Teacher teacher, int rating, String comment) {
        if (rating < 1 || rating > 5) { System.out.println("Rating must be 1-5."); return; }
        teacher.receiveRating(rating, comment, this);
        System.out.println(getFullName() + " rated " + teacher.getFullName() + ": " + rating + "/5");
    }
 
    public void addCourse(Course c) { if (!enrolledCourses.contains(c)) enrolledCourses.add(c); }
    public void removeCourse(Course c) { enrolledCourses.remove(c); }
 
    public void addMark(Mark m) {
        transcript.add(m);
        recalcGpa();
    }
 
    private void recalcGpa() {
        gpa = transcript.stream().mapToDouble(Mark::getScore).average().orElse(0) / 25.0;
        gpa = Math.min(gpa, 4.0);
    }
 
    // observer
 
    @Override
    public void update(String eventType, String message) {
        String entry = "[" + eventType + "] " + message;
        inbox.add(entry);
        System.out.println("📬 " + getFullName() + " received: " + entry);
    }
 
    // getters
 
    public String        getStudentId()       { return studentId; }
    public StudentDegree getDegree()          { return degree; }
    public int           getYear()            { return year; }
    public void          setYear(int y)       { this.year = y; }
    public double        getGpa()             { return gpa; }
    public List<String>  getInbox()           { return new ArrayList<>(inbox); }
    public List<Course>  getEnrolledCourses() { return new ArrayList<>(enrolledCourses); }
    public List<Mark>    getTranscript()      { return new ArrayList<>(transcript); }
    
}
