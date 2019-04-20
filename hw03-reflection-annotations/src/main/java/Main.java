import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        RunTest runTest=new RunTest();
        runTest.start(UserTest.class);
    }
}
