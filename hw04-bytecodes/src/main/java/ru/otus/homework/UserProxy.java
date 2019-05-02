package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxy  {

    static public DemoInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new DemoImpl());
        return (DemoInterface) Proxy.newProxyInstance(UserProxy.class.getClassLoader(),
                new Class<?>[]{DemoInterface.class}, handler);

    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final DemoInterface myClass;


        DemoInvocationHandler(DemoInterface myClass) {
            this.myClass = myClass;
        }

        @Override

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method[] methods = myClass.getClass().getMethods();

            method.invoke(myClass, args);
            for (Method temp : methods) {
                if (temp.getAnnotation(Log.class) != null) {
                    System.out.println ("Метод: "+temp.getName()+" Параметр: "+temp.);
                }
            }

            return null;
        }
    }
}
