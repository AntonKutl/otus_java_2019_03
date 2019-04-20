
import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTest {
    public static void start(Class userClass) throws InvocationTargetException, IllegalAccessException, InstantiationException {


        Method[] metods = userClass.getMethods();
        Object testObject=userClass.newInstance();


        for (Method o : metods) {
            if (o.getAnnotation(Before.class) != null) {
                try {
                    o.invoke(testObject);
                }catch (Exception e){
                    System.out.println("Ошибка: "+e);
                }
            }
        }
        for (Method o : metods) {
            if (o.getAnnotation(Test.class) != null) {
                try {
                    o.invoke(testObject);
                }catch (Exception e){
                    System.out.println("Ошибка"+e);
                }
            }
        }

        for (Method o : metods) {
            if (o.getAnnotation(After.class) != null) {
                try {
                    o.invoke(testObject);
                }catch (Exception e){
                    System.out.println("Ошибка"+e);
                }
            }
        }
    }
}
