package model;

public class PartTimeEmployee extends Employee {
    private int hoursPerWeek;

    public PartTimeEmployee(String employeeID, String name, int age, String gender,
                            String departmentID, String position, double salary,
                            String phone, int hoursPerWeek) {
        super(employeeID, name, age, gender, departmentID, position, salary, phone);
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public double calculateBonus() {
        return hoursPerWeek * 50000; // 50k/giờ
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Part-time | Giờ/tuần: " + hoursPerWeek;
    }
}