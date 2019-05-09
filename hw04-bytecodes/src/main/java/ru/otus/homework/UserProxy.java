package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.logging.Logger;

public class UserProxy {

    static public DemoInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new DemoImpl());
        return (DemoInterface) Proxy.newProxyInstance(UserProxy.class.getClassLoader(),
                new Class<?>[]{DemoInterface.class}, handler);

    }

    static class DemoInvocationHandler implements InvocationHandler {
        DemoInterface myClass;
        Logger log = Logger.getLogger(UserProxy.class.getName());
        Method[] methods;
        Set<String> methodLog = new HashSet<>();

        DemoInvocationHandler(DemoInterface myClass) {
            this.myClass = myClass;
            methods = myClass.getClass().getMethods();
            System.out.println(Arrays.toString(methods));
            for (Method temp : methods) {
                if (temp.getAnnotation(Log.class) != null) {
                    methodLog.add(temp.getName());

                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (methodLog.contains(method.getName())) {
                log.info("Метод: " + method.getName() + " Параметр: " + Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }
    }
}
