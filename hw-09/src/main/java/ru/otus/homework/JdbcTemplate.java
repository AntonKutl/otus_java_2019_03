package ru.otus.homework;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface  JdbcTemplate <T> {
    void create(String create) throws SQLException;
    void update(T objectData) throws IllegalAccessException, SQLException;
    <T> T load(long id, Class<T> clazz) throws SQLException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;
}
