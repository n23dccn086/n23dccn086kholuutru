package model;

public abstract class Person {
    private String name;
    private int age;
    private String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        setAge(age);
        setGender(gender);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age <= 0) throw new IllegalArgumentException("Tuổi phải > 0");
        this.age = age;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Giới tính không được để trống");
        }
        String g = gender.trim().toLowerCase();
        if (!g.equals("nam") && !g.equals("nữ")) {
            throw new IllegalArgumentException("Giới tính chỉ được là 'Nam' hoặc 'Nữ'");
        }
        this.gender = g.equals("nam") ? "Nam" : "Nữ";
    }

    public abstract String getInfo();
}