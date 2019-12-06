package otus.dao;

import java.util.List;

public interface DAOUser<T> {

    void save(T objectData);
    List<T> readAll();
}
