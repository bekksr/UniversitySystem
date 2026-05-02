package model;

import java.util.Date;

public class Admin extends Employee {
    public String role;

    public Admin(int id, String name, String email, String password,
                 double salary, Date hireDate, String department, String role) {
        super(id, name, email, password, salary, hireDate, department);
        this.role = role;
    }

    public boolean addUser(User u) {
        if (u == null) return false;
        System.out.println("User added: " + u.name);
        return true;
    }

    public boolean removeUser(User u) {
        if (u == null) return false;
        System.out.println("User removed: " + u.name);
        return true;
    }

    public boolean updateUser(User u) {
        if (u == null) return false;
        System.out.println("User updated: " + u.name);
        return true;
    }

    public void viewLogs() {
        System.out.println("Viewing system logs...");
    }
}
