package model;

public abstract class Employee extends Person {
    private String employeeID;
    private String departmentID;
    private String position;
    private double salary;
    private String phone;

    public Employee(String employeeID, String name, int age, String gender,
                    String departmentID, String position, double salary, String phone) {
        super(name, age, gender);
        this.employeeID = employeeID;
        this.departmentID = departmentID;
        this.position = position;
        setSalary(salary);
        this.phone = phone;
    }

    public String getEmployeeID() { return employeeID; }
    public String getDepartmentID() { return departmentID; }
    public void setDepartmentID(String departmentID) { this.departmentID = departmentID; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Lương không âm");
        this.salary = salary;
    }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public abstract double calculateBonus();

    @Override
    public String getInfo() {
        return String.format("ID: %s | %s | Lương: %,.0f | Bonus: %,.0f",
                employeeID, getName(), salary, calculateBonus());
    }
}