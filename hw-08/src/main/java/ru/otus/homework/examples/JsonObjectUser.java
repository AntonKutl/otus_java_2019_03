package ru.otus.homework.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonObjectUser {
    int[] ints={1,2,3,4,5};
    Person [] people={new Person("Петр",18),new Person("Сергей", 22)};
    List<Person> personList= new ArrayList<>();
    public JsonObjectUser() {
        personList.add(new Person("Jon",25));
        personList.add(new Person("Anton",28));
    }

    @Override
    public String toString() {
        return "JsonObjectUser{" +
                "ints=" + Arrays.toString(ints) +
                ", people=" + Arrays.toString(people) +
                ", personList=" + personList +
                '}';
    }
}
