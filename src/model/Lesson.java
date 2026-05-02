package model;

import enums.LessonType;
import java.util.Date;

public class Lesson {
    public LessonType type;
    public Date date;

    private String topic;
    private Course course;

    public Lesson(LessonType type, Date date) {
        this.type = type;
        this.date = date;
    }

    public Lesson(LessonType type, Date date, String topic) {
        this.type = type;
        this.date = date;
        this.topic = topic;
    }

    public String getInfo() {
        return "Lesson [type=" + type + ", date=" + date +
               (topic != null ? ", topic=" + topic : "") + "]";
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
