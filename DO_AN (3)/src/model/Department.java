package model;

public class Department {
    private String departmentID;
    private String name;
    private String description;
    private int maxEmployees;

    public Department(String departmentID, String name, String description, int maxEmployees) {
        this.departmentID = departmentID;
        this.name = name;
        this.description = description;
        this.maxEmployees = maxEmployees;
    }

    public String getDepartmentID() { return departmentID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getMaxEmployees() { return maxEmployees; }
    public void setMaxEmployees(int maxEmployees) { this.maxEmployees = maxEmployees; }

    @Override
    public String toString() {
        return departmentID + " - " + name;
    }
}