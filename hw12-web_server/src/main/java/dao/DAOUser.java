package dao;

import java.util.List;

public interface DAOUser<T> {

    void save(T objectData);
    <T> T read(String name, Class<T> clazz);
    List<T> readAll ();
}
