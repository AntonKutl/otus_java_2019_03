package ru.otus.homework;

public interface  JdbcTemplate <T> {
    void create(T objectData);
    void update(T objectData);
    <T> T load(long id, Class<T> clazz);
}
