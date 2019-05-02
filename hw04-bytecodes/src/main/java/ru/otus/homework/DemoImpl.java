package ru.otus.homework;

public class DemoImpl implements DemoInterface {

    @Log
    public void calculation(int param) {
        System.out.println(param);
    }

}
