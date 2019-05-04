
import ru.otus.homework.DemoInterface;
import ru.otus.homework.UserProxy;

public class Main {
    public static void main(String[] args) {
        DemoInterface demoInterface=UserProxy.createMyClass();
        demoInterface.calculation(5);
        demoInterface.calculation(6);
    }
}
