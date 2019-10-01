package dao;

import model.User;

public interface DAOUser<T> {

    void save(User objectData);
    <T> T read(long id, Class<T> clazz);
}
