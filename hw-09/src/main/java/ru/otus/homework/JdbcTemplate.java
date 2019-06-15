package ru.otus.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JdbcTemplate<T> {
    private String nameTable;
    private Long id = null;
    private String nameFieldString;
    private String valueFieldString;
    private String nameFieldInt;
    private int valueFieldInt;
    private static final String URL = "jdbc:h2:mem:";
    private Connection connection;

    public JdbcTemplate() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    public void create(Class clazz) throws SQLException {
        reflectClass(clazz);
        try (PreparedStatement pst = connection.prepareStatement("create table "+nameTable+"(id bigint(20) NOT NULL auto_increment, "+nameFieldString+" varchar(255),"+nameFieldInt+" int(3))")) {
            pst.executeUpdate();
        }
    }

    public void update(T objectData) throws IllegalAccessException, SQLException {
        reflectObject(objectData);
        if (!(id == null)) {
            try (PreparedStatement pst = connection.prepareStatement("insert into " + nameTable + "(id, " + nameFieldString + ", " + nameFieldInt + " ) values (?, ?, ?)")) {
                Savepoint savePoint = this.connection.setSavepoint("savePointName");
                pst.setLong(1, id);
                pst.setString(2, valueFieldString);
                pst.setLong(3, valueFieldInt);

                try {
                    pst.executeUpdate();
                    this.connection.commit();
                } catch (SQLException ex) {
                    this.connection.rollback(savePoint);
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    public <T> T load(long id, Class clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class[] param = {long.class, String.class, int.class};
        reflectClass(clazz);
        try (PreparedStatement pst = this.connection.prepareStatement("select * from " + nameTable + " where id  = ?")) {
            pst.setInt(1, Math.toIntExact(id));

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    valueFieldString = rs.getString(nameFieldString);
                    valueFieldInt = rs.getInt(nameFieldInt);
                }
            }
        }

        return (T) clazz.getConstructor(param).newInstance(id, valueFieldString, valueFieldInt);
    }

    private void reflectObject(Object obj) throws IllegalAccessException {
        reflectClass(obj.getClass());
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            if (tempField.getAnnotation(Id.class) != null) {
                id = (long) tempField.get(obj);
            } else if (tempField.getType().getSimpleName().equals("String")) {
                valueFieldString = tempField.get(obj).toString();

            } else if (tempField.getType().getSimpleName().equals("int")) {
                valueFieldInt = (int) tempField.get(obj);
            }
        }
    }

    private void reflectClass(Class clazz) {
        nameTable = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            if (tempField.getType().getSimpleName().equals("String")) {
                nameFieldString = tempField.getName();

            } else if (tempField.getType().getSimpleName().equals("int")) {
                nameFieldInt = tempField.getName();
            }
        }
    }

}
