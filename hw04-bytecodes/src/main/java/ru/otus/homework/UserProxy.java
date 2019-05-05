package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.logging.Logger;

public class UserProxy {

    static public DemoInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new DemoImpl());
        return (DemoInterface) Proxy.newProxyInstance(UserProxy.class.getClassLoader(),
                new Class<?>[]{DemoInterface.class}, handler);

    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final DemoInterface myClass;
        private Logger log = Logger.getLogger(UserProxy.class.getName());
        Method[] methods;
        Method metod;


        DemoInvocationHandler(DemoInterface myClass) {
            this.myClass = myClass;
            methods = myClass.getClass().getMethods();

            for (Method temp : methods) {
                if (temp.getAnnotation(Log.class) != null) {
                    metod = temp;
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("Метод: " + method.getName() + " Параметр: " + Arrays.toString(args));
            method.invoke(myClass, args);
            return null;
        }
    }
}
