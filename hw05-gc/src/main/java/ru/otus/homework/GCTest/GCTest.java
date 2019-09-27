package ru.otus.homework.GCTest;

import java.util.ArrayList;
import java.util.List;

public class GCTest {

    private static int count=0;
    public static void run() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        boolean cycle = true;
        while (cycle = true) {
            count=list.size();
            for (int i = list.size(); i < count + 7; i++) {
                list.add(i);
                System.out.println(list.size());
            }
            count=list.size();
            for (int i = list.size(); i > count - 1; i--) {
                list.remove(i-1);
                System.out.println(list.size());
            }

        }
    }

}
