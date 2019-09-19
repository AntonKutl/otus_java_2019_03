package ru.otus.homework.dao;

public interface DAOUser<T> {
    //void create(T objectData);
    void save(T objectData);
    <T> T read(long id, Class<T> clazz);
}
