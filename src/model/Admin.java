package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Admin extends Employee {
    private final List<String> systemLogs = new ArrayList<>();
 
    public Admin(String firstName, String lastName, String email, String password, double salary) {
        super(firstName, lastName, email, password, salary, "Administration");
    }
 
    @Override public void work() {
        System.out.println(getFullName() + " [Admin] is managing system operations.");
    }
 
    public List<String> viewLogs() {
        System.out.println("=== System Logs ===");
        systemLogs.forEach(l -> System.out.println("  " + l));
        return new ArrayList<>(systemLogs);
    }
 
    public void log(String event) {
        systemLogs.add("[" + LocalDateTime.now() + "] " + event);
    }
 
    public void deleteUser(User u) {
        log("DELETED: " + u.getFullName() + " id=" + u.getId());
    }
 
    public void resetPassword(User u, String newPw) {
        u.setPassword(newPw);
        log("PASSWORD RESET: " + u.getFullName());
    }
 
    public List<String> getSystemLogs() { return new ArrayList<>(systemLogs); }
    
}
