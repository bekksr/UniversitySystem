package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Employee {

    public Manager(int id, String name, String email, String password,
                   double salary, Date hireDate, String department) {
        super(id, name, email, password, salary, hireDate, department);
    }

    public boolean assignCourse(Teacher t, Course c) {
        if (t == null || c == null) return false;
        return t.manageCourse(c);
    }

    public boolean addCourse(Course c) {
        if (c == null) return false;
        System.out.println("Course added: " + c.name);
        return true;
    }

    public boolean manageUsers() {
        System.out.println("Managing users...");
        return true;
    }

    public List<Student> viewStudents() {
        System.out.println("Viewing all students...");
        return new ArrayList<>();
    }

    public void manageNews() {
        System.out.println("Managing news...");
    }

    public List<String> viewRequests() {
        return new ArrayList<>();
    }

    public List<Student> getStudentsSorted() {
        return new ArrayList<>();
    }
}
