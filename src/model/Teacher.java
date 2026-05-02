package model;

import enums.TeacherType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Teacher extends Employee {
    public TeacherType type;

    private List<Course> courses;

    public Teacher(int id, String name, String email, String password,
                   double salary, Date hireDate, String department, TeacherType type) {
        super(id, name, email, password, salary, hireDate, department);
        this.type = type;
        this.courses = new ArrayList<>();
    }

    public boolean manageCourse(Course c) {
        if (c == null) return false;
        if (!courses.contains(c)) {
            courses.add(c);
            c.addTeacher(this);
        }
        return true;
    }

    public List<Student> viewStudents(Course c) {
        if (c == null) return new ArrayList<>();
        return c.getStudents();
    }

    public boolean sendMessage(Employee e, String message) {
        if (e == null || message == null || message.isEmpty()) {
            return false;
        }
        System.out.println("[Message from " + name + " to " + e.name + "]: " + message);
        return true;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
}
