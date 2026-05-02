package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Employee extends User implements Researcher {
    public double salary;
    public Date hireDate;
    public String department;

    private List<ResearchPaper> papers;

    public Employee(int id, String name, String email, String password,
                    double salary, Date hireDate, String department) {
        super(id, name, email, password);
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        this.papers = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public boolean setSalary(double s) {
        if (s >= 0) {
            this.salary = s;
            return true;
        }
        return false;
    }

    public String work() {
        return name + " is working in " + department;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort(c);
        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }
    }

    @Override
    public int getHIndex() {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));
        int h = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getCitations() >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }
}
