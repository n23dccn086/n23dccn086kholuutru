package manager;
import java.util.List;
public interface IManager<T> {
    void add(T item);
    void remove(String id);
    void update(T item);
    T search(String id);
    List<T> getAll();
    void saveToFile();
    void loadFromFile();
    void statistics();
}