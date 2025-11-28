package manager;

import model.*;

import java.io.*;
import java.util.*;

public class EmployeeManager implements IManager<Employee> {

    private List<Employee> list = new ArrayList<>();
    final String FILE = "employee.txt";

    public EmployeeManager() {
        loadFromFile();
    }

    @Override public void add(Employee e) { list.add(e); saveToFile(); }

    @Override public void remove(String id) {
        list.removeIf(e -> e.getEmployeeID().equalsIgnoreCase(id));
        saveToFile();
    }

    @Override public void update(Employee e) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployeeID().equalsIgnoreCase(e.getEmployeeID())) {
                list.set(i, e);
                saveToFile();
                return;
            }
        }
    }

    @Override public Employee search(String id) {
        return list.stream()
                   .filter(e -> e.getEmployeeID().equalsIgnoreCase(id))
                   .findFirst()
                   .orElse(null);
    }

    @Override public List<Employee> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            pw.println("=== EMPLOYEE ===");
            for (Employee e : list) {
                String type = e instanceof FullTimeEmployee ? "full" : "part";
                pw.println(e.getEmployeeID() + "|" + e.getName() + "|" + e.getAge() + "|" + e.getGender() + "|" +
                           e.getDepartmentID() + "|" + e.getPosition() + "|" + e.getSalary() + "|" + e.getPhone() + "|" + type);
            }
        } catch (IOException ex) {
            System.out.println("Lỗi lưu nhân viên: " + ex.getMessage());
        }
    }

    @Override
    public void loadFromFile() {
        list.clear();
        File f = new File(FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean reading = false;
            while ((line = br.readLine()) != null) {
                if (line.equals("=== EMPLOYEE ===")) {
                    reading = true;
                    continue;
                }
                if (reading && !line.trim().isEmpty()) {
                    String[] p = line.split("\\|");
                    if (p.length >= 9) {
                        String type = p[8];
                        Employee e = type.equals("full") ?
                            new FullTimeEmployee(p[0], p[1], Integer.parseInt(p[2]), p[3], p[4], p[5], Double.parseDouble(p[6]), p[7], 24) :
                            new PartTimeEmployee(p[0], p[1], Integer.parseInt(p[2]), p[3], p[4], p[5], Double.parseDouble(p[6]), p[7], 20);
                        list.add(e);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Lỗi đọc nhân viên: " + ex.getMessage());
        }
    }

    @Override
    public void statistics() {
        long full = list.stream().filter(e -> e instanceof FullTimeEmployee).count();
        long part = list.size() - full;
        double avg = list.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);

        System.out.println("\n=== THỐNG KÊ NHÂN VIÊN ===");
        System.out.println("Tổng nhân viên : " + list.size());
        System.out.println("Full-time      : " + full);
        System.out.println("Part-time      : " + part);
        System.out.printf("Lương trung bình: %, .0f VNĐ%n", avg);
        System.out.println("==================================\n");
    }
    // Sắp xếp nhân viên theo ID
    public List<Employee> sortByID() {
        List<Employee> sortedList = new ArrayList<>(list);
        sortedList.sort((e1, e2) -> e1.getEmployeeID().compareToIgnoreCase(e2.getEmployeeID()));
        return sortedList;
    }

    public List<Employee> sortByName() {
        List<Employee> sortedList = new ArrayList<>(list);
        sortedList.sort((e1, e2) -> {
            String lastName1 = getLastName(e1.getName());
            String lastName2 = getLastName(e2.getName());
            return lastName1.compareToIgnoreCase(lastName2);
        });
        return sortedList;
    }

    private String getLastName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String[] nameParts = fullName.trim().split("\\s+");
        return nameParts[nameParts.length - 1]; // Lấy chữ cuối cùng
    }

    // Sắp xếp nhân viên theo lương
    public List<Employee> sortBySalary() {
        List<Employee> sortedList = new ArrayList<>(list);
        sortedList.sort((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        return sortedList;
    }
    public List<Employee> sortByAge() {
        List<Employee> sortedList = new ArrayList<>(list);
        sortedList.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        return sortedList;
    }


}