package model;

import enums.TeacherType;
import observer.Observer;
 
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee implements Researcher, Observer {
    private TeacherType teacherType;
    private boolean     researchEnabled;
 
    private final List<Course>          assignedCourses = new ArrayList<>();
    private final List<Student>         students        = new ArrayList<>();
    private final List<ResearchPaper>   publications    = new ArrayList<>();
    private final List<ResearchProject> projects        = new ArrayList<>();
    private final List<Integer>         ratings         = new ArrayList<>();
    private final List<String>          inbox           = new ArrayList<>();
 
    public Teacher(String firstName, String lastName, String email, String password,
                   double baseSalary, String department, TeacherType teacherType) {
        super(firstName, lastName, email, password, baseSalary, department);
        this.teacherType     = teacherType;
        // profs are always researchers
        this.researchEnabled = teacherType.isAlwaysResearcher();
    }
 
    // research toggle
 
    public void enableResearch() { this.researchEnabled = true; }
    public boolean isResearcher() { return researchEnabled; }
 
    // employee
 
    @Override
    public void work() {
        System.out.println(getFullName() + " [" + teacherType + "] is conducting classes.");
    }
 
    @Override
    public double salary() {
        double bonus = switch (teacherType) {
            case PROFESSOR            -> 0.40;
            case ASSOCIATE_PROFESSOR  -> 0.25;
            case SENIOR_LECTURER      -> 0.15;
            case LECTURER             -> 0.05;
            case TUTOR                -> 0.0;
        };
        return getBaseSalary() * (1 + bonus);
    }
 
    // teacher actions
 
    public List<Course> manageCourse() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        assignedCourses.forEach(c -> System.out.println("  " + c));
        return new ArrayList<>(assignedCourses);
    }
 
    public List<Student> viewStudents() {
        System.out.println("=== Students of " + getFullName() + " ===");
        students.forEach(s -> System.out.println("  " + s));
        return new ArrayList<>(students);
    }
 
    public void sendMessage(Student student, String message) {
        student.update("MESSAGE", "[From " + getFullName() + "]: " + message);
    }
 
    public void addCourse(Course c)   { if (!assignedCourses.contains(c)) assignedCourses.add(c); }
    public void addStudent(Student s) { if (!students.contains(s)) students.add(s); }
 
    public void receiveRating(int rating, String comment, Student from) {
        ratings.add(rating);
        System.out.println("Rating received from " + from.getFullName() + ": " + rating + "/5 — " + comment);
    }
 
    public double getAverageRating() {
        return ratings.stream().mapToInt(i -> i).average().orElse(0.0);
    }
 
    // researcher interface
 
    @Override public List<ResearchPaper>   getPublications()         { return new ArrayList<>(publications); }
    @Override public void                  addPublication(ResearchPaper p) { publications.add(p); }
    @Override public List<ResearchProject> getProjects()             { return new ArrayList<>(projects); }
    @Override public void                  addProject(ResearchProject p)   { projects.add(p); }
 
    //observer
 
    @Override
    public void update(String eventType, String message) {
        String entry = "[" + eventType + "] " + message;
        inbox.add(entry);
        System.out.println("📬 " + getFullName() + " received: " + entry);
    }
 
    // getters
 
    public TeacherType   getTeacherType()     { return teacherType; }
    public void          setTeacherType(TeacherType t) { this.teacherType = t; }
    public List<Course>  getAssignedCourses() { return new ArrayList<>(assignedCourses); }
    public List<Integer> getRatings()         { return new ArrayList<>(ratings); }
    public List<String>  getInbox()           { return new ArrayList<>(inbox); }
}
