package demo;

import storage.DataStorage;
import service.ReportGenerator;
import model.Student;
import model.Course;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataStorage storage = DataStorage.getInstance();
        storage.loadData();
        
        Scanner scanner = new Scanner(System.in);
        ReportGenerator reportGenerator = new ReportGenerator();
        boolean running = true;

        System.out.println("==========================================");
        System.out.println(" Welcome to the University Management System ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Data Storage Statistics");
            System.out.println("2. Generate Student Report Demo");
            System.out.println("3. Generate Course Report Demo");
            System.out.println("4. Generate Marks Statistics Demo");
            System.out.println("5. Add Dummy Data (For testing)");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Storage Stats ---");
                    System.out.println("Total Users: " + storage.getUsers().size());
                    System.out.println("Total Courses: " + storage.getCourses().size());
                    System.out.println("Total Research Projects: " + storage.getProjects().size());
                    break;
                case "2":
                    Student demoStudent = new Student(1, "Alikhan", "ali@kbtu.kz", "pass", 3.8, 2);
                    System.out.println("\n" + reportGenerator.generateStudentReport(demoStudent));
                    break;
                case "3":
                    Course demoCourse = new Course("Object-Oriented Programming", 3);
                    System.out.println("\n" + reportGenerator.generateCourseReport(demoCourse));
                    break;
                case "4":
                    Course statsCourse = new Course("Databases", 3);
                    System.out.println("\n" + reportGenerator.generateMarksStatistics(statsCourse));
                    break;
                case "5":
                    storage.getUsers().add(new Student(2, "Test Student", "test@kbtu.kz", "pass", 3.0, 1));
                    storage.getCourses().add(new Course("Software Engineering", 3));
                    System.out.println("\nDummy data added. Try option 1 to see changes.");
                    break;
                case "6":
                    System.out.println("\nSaving data...");
                    storage.saveData();
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
