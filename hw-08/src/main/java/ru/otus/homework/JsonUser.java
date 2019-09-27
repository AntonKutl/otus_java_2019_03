package ru.otus.homework;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class JsonUser {
    String str = "{";


    public String json(Object object) throws IOException, IllegalAccessException {

        traverse(object);
        System.out.println(str);
        String temp=str.replace(",}","}");
        str=temp.replace(",]","]");
        //System.out.println(str);
        return str;
    }

    private void traverse(Object object) throws IllegalAccessException, IOException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                fieldPrimitive(field, object);
            } else if (field.getType().isArray()) {
                fieldArray(field, object);
            } else if (Collection.class.isAssignableFrom(field.getType())) {
                fieldCollection(field, object);
            } else if (field.getType().getSimpleName().equals("String")) {
                fieldString(field, object);
            } else if (field.getClass().getSuperclass().getSimpleName().equals("Object")){
                fieldMemberClass(field, object);
            }

        }
        str = str + "}";
    }

    private void fieldString(Field field, Object object) throws IllegalAccessException {
        str = str + "{\"" + field.getName() + "\":\"" + field.get(object).toString() + "\",";
    }

    private void fieldPrimitive(Field field, Object object) throws IllegalAccessException {
        str = str + "\"" + field.getName() + "\":" + field.get(object).toString() + ",";
    }

    private void fieldMemberClass(Field field, Object object) throws IllegalAccessException, IOException {
        str = str + "{\"" + field.getName() + "\":";
        traverse(field.get(object));
        str = str + "},";
    }

    private void fieldArray(Field field, Object object) throws IllegalAccessException, IOException {
        str = str + "\"" + field.getName() + "\":[";
        Object array =field.get(object);
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                str = str + ",";
            }
            if (Array.get(array, i).getClass().getSuperclass().getSimpleName().equals("Object")){
                traverse(Array.get(array, i));
            }else  str = str +Array.get(array, i).toString();

        }
        str = str + "],";
    }

    private void fieldCollection(Field field, Object object) throws IllegalAccessException, IOException {
        str = str + "\"" + field.getName() + "\":[";
        Collection<?> collection = (Collection<?>) field.get(object);
        for (Object temp : collection) {
            traverse(temp);
            str = str + ",";
        }
        str = str + "],";
    }
}
