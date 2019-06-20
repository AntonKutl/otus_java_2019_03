package ru.otus.homework;

import java.lang.reflect.Field;
import java.sql.*;

public class JdbcTemplateImpl<T> implements JdbcTemplate<T> {
    private static final String URL = "jdbc:h2:mem:";
    private Connection connection;

    public <T> JdbcTemplateImpl() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    @Override
    public void create(String create) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(create)) {
            pst.executeUpdate();
        }
    }

    @Override
    public void update(T objectData) throws IllegalAccessException, SQLException {
        StringBuilder valueField = new StringBuilder();
        StringBuilder nameField = new StringBuilder();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            valueField.append("?,");
            nameField.append(tempField.getName() + ",");
        }
        valueField.setLength(valueField.length() - 1);
        nameField.setLength(nameField.length() - 1);

        try (PreparedStatement pst = connection.prepareStatement("insert into " + objectData.getClass().getSimpleName() +
                "(" + nameField + " ) values (" + valueField + ")")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            for (int i = 1; i < fields.length + 1; i++) {
                pst.setObject(i, fields[i - 1].get(objectData));
            }
            try {
                pst.executeUpdate();
                this.connection.commit();
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public T load(long id, Class clazz) throws SQLException, IllegalAccessException, InstantiationException {
        try (PreparedStatement pst = this.connection.prepareStatement("select * from " + clazz.getSimpleName() + " where " + nameID(clazz) + "  = ?")) {
            pst.setObject(1, id);
            return transformation(pst, clazz);
        }
    }

    private T transformation(PreparedStatement pst, Class clazz) throws IllegalAccessException, InstantiationException, SQLException {
        T object = (T) clazz.newInstance();
        try (ResultSet rs = pst.executeQuery()) {
            Field[] fields = clazz.getDeclaredFields();
            if (rs.next()) {
                for (Field field : fields)
                    if (field.getType() == String.class) {
                        field.set(object, rs.getString(field.getName()));
                    } else if (field.getType() == int.class) {
                        field.set(object, rs.getInt(field.getName()));
                    } else if (field.getType() == long.class) {
                        field.set(object, rs.getLong(field.getName()));
                    }
            }
        }

        return object;
    }


    private String nameID(Class clazz){
        String nameId = "";
        Field[] fields = clazz.getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            if (tempField.getAnnotation(Id.class) != null) {
                nameId = tempField.getName();
            }
        }
        return nameId;
    }
}


