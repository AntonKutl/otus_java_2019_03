package otus.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user")

public class User implements Serializable {
    public User() {
    }
    public User(String name, int age, String phone, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User {name=" + name  +
                ", age=" + age +
                ", address=" + address  +
                ", phone=" + phone +
                '}';
    }
}


