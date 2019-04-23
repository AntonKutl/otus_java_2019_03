package ru.otus.homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RunTest {
    public static void start(Class userClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {


        Method[] metods = userClass.getMethods();
        List<Method> metodsTest = new ArrayList();

        for (Method temp : metods) {
            if (temp.getAnnotation(Test.class) != null) {
                metodsTest.add(temp);
            }
        }

        for (int i = 0; i < metodsTest.size(); i++) {
            Object testObject = userClass.newInstance();
            try {
                for (Method o : metods) {
                    if (o.getAnnotation(Before.class) != null) {
                        o.invoke(testObject);
                    }
                }
                metodsTest.get(i).invoke(testObject);

                for (Method o : metods) {
                    if (o.getAnnotation(After.class) != null) {
                        o.invoke(testObject);
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка " + e);
            }

            System.out.println("Цикл с тестом завершен ");
            System.out.println("----------------------");
        }
    }
}
