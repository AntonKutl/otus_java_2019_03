package ru.otus.homework.dao;

public interface DAOUser<T> {
    void create(T objectData);
    void update(T objectData);
    <T> T load(long id, Class<T> clazz);
}
