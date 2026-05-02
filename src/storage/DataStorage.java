package storage;

import model.User;
import model.Course;
import model.ResearchProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "database.ser";

    private List<User> users = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<ResearchProject> projects = new ArrayList<>();
    
    private static DataStorage instance;

    private DataStorage() {}

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public boolean saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            return false;
        }
    }

    public boolean loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            DataStorage loaded = (DataStorage) ois.readObject();
            this.users = loaded.users;
            this.courses = loaded.courses;
            this.projects = loaded.projects;
            System.out.println("Data loaded successfully.");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
            return false;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return false;
        }
    }

    public List<User> getUsers() { return users; }
    public List<Course> getCourses() { return courses; }
    public List<ResearchProject> getProjects() { return projects; }
}
