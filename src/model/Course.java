package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String name;
    public int credits;

    private List<Teacher> teachers;
    private List<Student> students;
    private List<Lesson> lessons;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public boolean addStudent(Student s) {
        if (s == null || students.contains(s)) {
            return false;
        }
        students.add(s);
        return true;
    }

    public boolean addTeacher(Teacher t) {
        if (t == null || teachers.contains(t)) {
            return false;
        }
        teachers.add(t);
        return true;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }

    public boolean addLesson(Lesson lesson) {
        if (lesson == null) return false;
        lesson.setCourse(this);
        lessons.add(lesson);
        return true;
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }

    public boolean removeStudent(Student s) {
        return students.remove(s);
    }

    @Override
    public String toString() {
        return name + " (" + credits + " credits)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
