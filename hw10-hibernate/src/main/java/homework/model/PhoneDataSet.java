package homework.model;

import javax.persistence.*;
@Entity
@Table(name = "phone_data")
public class PhoneDataSet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_number")
    private long id;
    @Column(name = "number")
    private String number;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  number;
    }
}
