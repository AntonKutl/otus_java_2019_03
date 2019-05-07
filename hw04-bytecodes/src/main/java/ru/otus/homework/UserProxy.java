package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class UserProxy {

    static public DemoInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new DemoImpl());
        return (DemoInterface) Proxy.newProxyInstance(UserProxy.class.getClassLoader(),
                new Class<?>[]{DemoInterface.class}, handler);

    }

    static class DemoInvocationHandler implements InvocationHandler {
        private DemoInterface myClass;
        private Logger log = Logger.getLogger(UserProxy.class.getName());
        Method[] methods;
        List<Method> methodLog = new ArrayList();


        DemoInvocationHandler(DemoInterface myClass) {
            this.myClass = myClass;
            methods = myClass.getClass().getMethods();
            for (Method temp : methods) {
                if (temp.getAnnotation(Log.class) != null) {
                    methodLog.add(temp);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            for (Method temp : methodLog) {
                if (temp.getName() == method.getName())
                    log.info("Метод: " + method.getName() + " Параметр: " + Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }
    }
}
