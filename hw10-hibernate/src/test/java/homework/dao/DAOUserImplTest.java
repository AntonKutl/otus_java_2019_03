package homework.dao;

import homework.model.AddressDataSet;
import homework.model.PhoneDataSet;
import homework.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserImplTest {

    User user=new User();
    DAOUser daoUser =new DAOUserImpl();

    @BeforeEach
    void setUp() {
        AddressDataSet addressDataSet=new AddressDataSet();
        PhoneDataSet phoneDataSet=new PhoneDataSet();
        phoneDataSet.setNumber("11111");
        addressDataSet.setStreet("Ленино");
        user.setId(1);
        user.setName("Anton");
        user.setAge(30);
        user.setAddress(addressDataSet);
        user.setPhoneDataSet(phoneDataSet);
    }

    @Test
    void save(){
        daoUser.save(user);
    }

    @Test
    void read(){
        assertEquals(user,daoUser.read(1,User.class));
    }

}