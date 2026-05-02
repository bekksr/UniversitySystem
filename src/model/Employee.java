package model;

public abstract class Employee extends User {
    private double baseSalary;
    private String department;
    private int    experienceYears;
 
    protected Employee(String firstName, String lastName, String email, String password,
                       double baseSalary, String department) {
        super(firstName, lastName, email, password);
        this.baseSalary      = baseSalary;
        this.department      = department;
        this.experienceYears = 0;
    }
 
    public double salary() { return baseSalary; }
 
    public abstract void work();
 
    public double getBaseSalary()             { return baseSalary; }
    public void   setBaseSalary(double s)     { this.baseSalary = s; }
    public String getDepartment()             { return department; }
    public void   setDepartment(String d)     { this.department = d; }
    public int    getExperienceYears()        { return experienceYears; }
    public void   setExperienceYears(int y)   { this.experienceYears = y; }
}
