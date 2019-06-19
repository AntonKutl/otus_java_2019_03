package ru.otus.homework;

import java.lang.reflect.Field;
import java.sql.*;

public class JdbcTemplateImpl implements JdbcTemplate {
    private static final String URL = "jdbc:h2:mem:";
    private Connection connection;

    public JdbcTemplateImpl() throws SQLException {
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
    public void update(Object objectData) throws IllegalAccessException, SQLException {
        StringBuilder valueField = new StringBuilder();
        StringBuilder nameField = new StringBuilder();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            if (tempField.getAnnotation(Id.class) != null) {
                valueField.append("?");
                nameField.append(tempField.getName());
            } else {
                valueField.append(",?");
                nameField.append("," + tempField.getName());
            }
        }

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
    public Object load(long id, Class clazz) throws SQLException, IllegalAccessException, InstantiationException {
        String nameId = "";
        Field[] fields = clazz.getDeclaredFields();
        for (Field tempField : fields) {
            tempField.setAccessible(true);
            if (tempField.getAnnotation(Id.class) != null) {
                nameId = tempField.getName();
            }
        }
        Object object = clazz.newInstance();
        try (PreparedStatement pst = this.connection.prepareStatement("select * from " + clazz.getSimpleName() + " where " + nameId + "  = ?")) {
            pst.setInt(1, Math.toIntExact(id));
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    for (Field field : fields) {
                        switch (field.getType().getSimpleName()) {
                            case "String":
                                field.set(object, rs.getString(field.getName()));
                                break;
                            case "int":
                                field.set(object, rs.getInt(field.getName()));
                                break;
                            case "long":
                                field.set(object, rs.getLong(field.getName()));
                                break;
                            case "float":
                                field.set(object, rs.getFloat(field.getName()));
                                break;


                        }

                    }
                }
            }


        }
        return object;
    }
}


