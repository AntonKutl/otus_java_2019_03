import com.google.common.base.Splitter;

public class HelloOtus {
    public static void main(String[] args) {

        System.out.println(Splitter.on(',')
                                   .trimResults()
                                   .omitEmptyStrings()
                                   .split("Hello, Otus"));
    }
}
