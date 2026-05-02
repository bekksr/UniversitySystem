package model;

import observer.Observable;
import observer.Observer;
 
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee implements Observable {
    private final List<Course>   managedCourses = new ArrayList<>();
    private final List<String>   newsBoard      = new ArrayList<>();
    private final List<String>   requests       = new ArrayList<>();
    private final List<Observer> observers      = new ArrayList<>();
 
    public Manager(String firstName, String lastName, String email, String password,
                   double baseSalary, String department) {
        super(firstName, lastName, email, password, baseSalary, department);
    }
 
    @Override public void work() {
        System.out.println(getFullName() + " [Manager] is overseeing department operations.");
    }
 
    // manager actions
 
    public void assignCourse(Course course, Teacher teacher) {
        course.addInstructor(teacher);
        if (!managedCourses.contains(course)) managedCourses.add(course);
        System.out.println(getFullName() + " assigned [" + course.getName() + "] to " + teacher.getFullName());
    }
 
    public void addCourse(Course course) {
        if (!managedCourses.contains(course)) managedCourses.add(course);
        System.out.println("Course added: " + course.getName());
    }
 
    public void manageNews(String news) {
        newsBoard.add(news);
        notifyObservers("NEWS", news);
    }
 
    public List<String> viewRequests() {
        System.out.println("=== Pending Requests ===");
        requests.forEach(r -> System.out.println("  - " + r));
        return new ArrayList<>(requests);
    }
 
    public void addRequest(String r)    { requests.add(r); }
    public void resolveRequest(String r){ requests.remove(r); System.out.println("Resolved: " + r); }
 
    // observable
 
    @Override public void addObserver(Observer o)    { observers.add(o); }
    @Override public void removeObserver(Observer o) { observers.remove(o); }
    @Override public void notifyObservers(String eventType, String message) {
        observers.forEach(o -> o.update(eventType, message));
    }
 
    // getters
 
    public List<Course> getManagedCourses() { return new ArrayList<>(managedCourses); }
    public List<String> getNewsBoard()      { return new ArrayList<>(newsBoard); }
    
}
