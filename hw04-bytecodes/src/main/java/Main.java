
import ru.otus.homework.DemoInterface;
import ru.otus.homework.UserProxy;

public class Main {
    public static void main(String[] args) {
        DemoInterface demoInterface=UserProxy.createMyClass();
        demoInterface.calculation(1);
        demoInterface.calculation2("Второй метод");
        demoInterface.calculation3( true);
    }
}
