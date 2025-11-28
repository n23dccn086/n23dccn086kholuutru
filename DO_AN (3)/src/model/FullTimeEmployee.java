package model;

public class FullTimeEmployee extends Employee {
    private int annualLeave = 24; // mặc định 24 ngày

    public FullTimeEmployee(String employeeID, String name, int age, String gender,
                            String departmentID, String position, double salary,
                            String phone, int annualLeave) {
        super(employeeID, name, age, gender, departmentID, position, salary, phone);
        this.annualLeave = annualLeave;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.10; // thưởng 10%
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Full-time | Nghỉ phép: " + annualLeave + " ngày";
    }
}