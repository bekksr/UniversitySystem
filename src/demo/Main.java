package demo;

import enums.*;
import model.*;
import service.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== UNIVERSITY SYSTEM DEMO =====\n");

        Course oop = new Course("OOP", 6);
        Course calculus = new Course("Calculus", 5);
        Course physics = new Course("Physics", 4);
        Course databases = new Course("Databases", 6);

        Lesson l1 = new Lesson(LessonType.LECTURE, new Date(), "Inheritance");
        Lesson l2 = new Lesson(LessonType.PRACTICE, new Date(), "Polymorphism Lab");
        oop.addLesson(l1);
        oop.addLesson(l2);
        System.out.println("Lesson info: " + l1.getInfo());
        System.out.println("Lesson info: " + l2.getInfo());
        System.out.println();

        Student s1 = new Student(1, "Olzhas", "olzhas@uni.kz", "pass123", 2);
        Student s2 = new Student(2, "Aisha", "aisha@uni.kz", "pass456", 2);
        Student s3 = new Student(3, "Timur", "timur@uni.kz", "pass789", 3);

        Teacher t1 = new Teacher(10, "Prof. Kanat", "kanat@uni.kz", "tpass",
                80000, new Date(), "CS", TeacherType.PROFESSOR);
        Teacher t2 = new Teacher(11, "Dr. Aliya", "aliya@uni.kz", "tpass2",
                60000, new Date(), "Math", TeacherType.LECTOR);

        t1.manageCourse(oop);
        t1.manageCourse(databases);
        t2.manageCourse(calculus);
        t2.manageCourse(physics);

        System.out.println("=== COURSE REGISTRATION ===");
        CourseRegistrationService regService = new CourseRegistrationService();

        regService.registerCourse(s1, oop);
        regService.registerCourse(s1, calculus);
        regService.registerCourse(s1, physics);
        regService.registerCourse(s1, databases);

        System.out.println("Total credits for " + s1.name + ": " + regService.getTotalCredits(s1));

        Course extra = new Course("AI", 3);
        regService.registerCourse(s1, extra);

        regService.registerCourse(s1, oop);

        System.out.println();

        regService.registerCourse(s2, oop);
        regService.registerCourse(s2, calculus);
        regService.registerCourse(s3, oop);

        System.out.println("=== PUTTING MARKS ===");
        MarkService markService = new MarkService();

        Mark m1 = new Mark(85, 90, 78);
        Mark m2 = new Mark(70, 65, 80);
        Mark m3 = new Mark(92, 88, 95);
        Mark m4 = new Mark(30, 40, 20);

        markService.putMark(t1, s1, oop, m1);
        markService.putMark(t2, s1, calculus, m2);
        markService.putMark(t1, s2, oop, m3);
        markService.putMark(t2, s2, calculus, m4);
        markService.putMark(t1, s3, oop, new Mark(75, 80, 70));

        markService.putMark(t1, s1, calculus, new Mark(50, 50, 50));
        System.out.println();

        System.out.println("=== GRADE CALCULATIONS ===");
        System.out.println("Olzhas OOP: " + m1.calculateGrade() + " -> " + m1.getLetterGrade());
        System.out.println("Olzhas Calculus: " + m2.calculateGrade() + " -> " + m2.getLetterGrade());
        System.out.println("Aisha OOP: " + m3.calculateGrade() + " -> " + m3.getLetterGrade());
        System.out.println("Aisha Calculus: " + m4.calculateGrade() + " -> " + m4.getLetterGrade());
        System.out.println();

        System.out.println("=== TRANSCRIPTS ===");
        System.out.println(s1.viewTranscript());
        System.out.println(s2.viewTranscript());

        System.out.println("Can Olzhas retake? " + s1.canRetake());
        System.out.println("Can Aisha retake? " + s2.canRetake());
        System.out.println();

        s1.rateTeacher(t1, 5);
        s2.rateTeacher(t2, 3);
        System.out.println();

        System.out.println("=== COURSE MARK REPORT ===");
        System.out.println(markService.generateMarkReport(oop));

        ReportGenerator reportGen = new ReportGenerator();
        System.out.println(reportGen.generateMarksStatistics(oop));

        System.out.println("=== TEACHER VIEW STUDENTS ===");
        System.out.println("Students in OOP:");
        for (Student s : t1.viewStudents(oop)) {
            System.out.println("  - " + s.name);
        }

        System.out.println("\n===== DEMO COMPLETE =====");
    }
}
