package manager;

import model.Department;

import java.io.*;
import java.util.*;

public class DepartmentManager implements IManager<Department> {

    private List<Department> list = new ArrayList<>();
    private final String FILE = "department.txt";

    public DepartmentManager() {
        loadFromFile();
    }

    @Override public void add(Department d) { list.add(d); saveToFile(); }

    @Override public void remove(String id) {
        list.removeIf(d -> d.getDepartmentID().equalsIgnoreCase(id));
        saveToFile();
    }

    @Override public void update(Department d) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDepartmentID().equalsIgnoreCase(d.getDepartmentID())) {
                list.set(i, d);
                saveToFile();
                return;
            }
        }
    }

    @Override public Department search(String id) {
        return list.stream()
                   .filter(d -> d.getDepartmentID().equalsIgnoreCase(id))
                   .findFirst()
                   .orElse(null);
    }

    @Override public List<Department> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            pw.println("=== DEPARTMENT ===");
            for (Department d : list) {
                pw.println(d.getDepartmentID() + "|" + d.getName() + "|" + d.getDescription() + "|" + d.getMaxEmployees());
            }
        } catch (IOException e) {
            System.out.println("Lỗi lưu phòng ban: " + e.getMessage());
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
                if (line.equals("=== DEPARTMENT ===")) {
                    reading = true;
                    continue;
                }
                if (line.startsWith("=== EMPLOYEE ===")) break;
                if (reading && !line.trim().isEmpty()) {
                    String[] p = line.split("\\|");
                    if (p.length == 4) {
                        list.add(new Department(p[0], p[1], p[2], Integer.parseInt(p[3])));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc phòng ban: " + e.getMessage());
        }
    }

    @Override
    public void statistics() {
        System.out.println("\n=== THỐNG KÊ PHÒNG BAN ===");
        System.out.println("Tổng số phòng ban: " + list.size());
        if (list.isEmpty()) {
            System.out.println("Chưa có phòng ban nào.");
        } else {
            list.forEach(d -> System.out.println("  • " + d.getDepartmentID() + " - " + d.getName() + " (Tối đa: " + d.getMaxEmployees() + " người)"));
        }
        System.out.println("====================================\n");
    }
}