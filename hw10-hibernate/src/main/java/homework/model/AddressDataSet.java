package homework.model;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDataSet that = (AddressDataSet) o;
        return street.equals(that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street);
    }
}
