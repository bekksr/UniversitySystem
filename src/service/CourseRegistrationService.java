package service;

import model.Course;
import model.Student;

public class CourseRegistrationService {

    private static final int MAX_CREDITS = 21;

    public boolean registerCourse(Student s, Course c) {
        if (s == null || c == null) return false;

        if (!canRegister(s, c)) {
            return false;
        }

        c.addStudent(s);
        s.addCourse(c);
        System.out.println(s.name + " registered for " + c.name);
        return true;
    }

    public boolean approveRegistration(Student s, Course c) {
        if (s == null || c == null) return false;

        if (!canRegister(s, c)) {
            System.out.println("Cannot approve: registration constraints not met.");
            return false;
        }

        c.addStudent(s);
        s.addCourse(c);
        System.out.println("Registration approved: " + s.name + " -> " + c.name);
        return true;
    }

    public boolean canRegister(Student s, Course c) {
        if (s == null || c == null) return false;

        if (s.viewCourses().contains(c)) {
            System.out.println("Student " + s.name + " is already registered for " + c.name);
            return false;
        }

        int currentCredits = getTotalCredits(s);
        if (currentCredits + c.credits > MAX_CREDITS) {
            System.out.println("Cannot register: " + currentCredits + " + " + c.credits +
                             " = " + (currentCredits + c.credits) + " exceeds max " + MAX_CREDITS + " credits.");
            return false;
        }

        return true;
    }

    public int getTotalCredits(Student s) {
        if (s == null) return 0;
        return s.getTotalCredits();
    }
}
