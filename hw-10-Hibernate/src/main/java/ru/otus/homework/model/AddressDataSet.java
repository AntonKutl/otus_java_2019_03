package ru.otus.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "address_data")
public class AddressDataSet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "street")
    private String street;

    @OneToOne()
    @JoinColumn(name = "id_user")
    private User user;



    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return street;
    }
}
