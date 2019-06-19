package ru.otus.homework;

public class User {
    @Id
    long id;
    String name;
    int age;
    int rs;

    public User() {
    }

    public User(long id, String name, int age, int rs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rs=rs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", rs=" + rs +
                '}';
    }
}
