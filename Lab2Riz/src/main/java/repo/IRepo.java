package repo;

import java.util.List;

public interface IRepo<T> {
    void insert(T object);
    void delete(Integer id);
    void update(T object);
    List<T> getAll();
}
