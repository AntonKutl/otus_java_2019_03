
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <Integer> list =new DIYarrayList<>(5);
        boolean b=Collections.addAll(list,21,13,54,21,28,35,3,1,58,6,47,12,32,47,548,154,1514,115,151,147,1444,44);
        System.out.println(list.toString());
        List <Integer> list2 =new DIYarrayList<>(list.size());
        boolean c=Collections.addAll(list2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

        Collections.copy(list2,list);
        System.out.println(list2.toString());

        ((DIYarrayList<Integer>) list).removeNull();

        Collections.sort(list,null);
        System.out.println(list.toString());

        List<Integer> list3 = new DIYarrayList<>(6);
        System.out.println(String.format("list size: %d, and list output: %s", list3.size(), list3.toString()));
        list3.set(5, 53);
        System.out.println(String.format("list size: %d, and list output: %s", list3.size(), list3.toString()));

    }

}
