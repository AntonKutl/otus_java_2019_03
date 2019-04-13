
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <Integer> list =new DIYarrayList<>(5);
        boolean b=Collections.addAll(list,21,13,54,21,28,35,3,1,58,6,47,12,32,47,548,154,1514,115,151,147,1444,44);
        System.out.println(list.toString());
        List <Integer> list2 =new DIYarrayList<>(list.size());
        boolean c=Collections.addAll(list2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

        Collections.sort(list,null);
        System.out.println(list.toString());

        Collections.copy(list2,list);
        System.out.println(list2.toString());

    }
}
