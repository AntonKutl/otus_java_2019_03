package homework;

import homework.dao.DAOUser;
import homework.dao.DAOUserImpl;
import homework.model.AddressDataSet;
import homework.model.PhoneDataSet;
import homework.model.User;

public class Main {
    public static void main(String[] args) {
        User user1=new User();
        AddressDataSet addressDataSet=new AddressDataSet();
        PhoneDataSet phoneDataSet=new PhoneDataSet();
        phoneDataSet.setNumber("123456789");
        addressDataSet.setStreet("A1");
        user1.setId(1);
        user1.setName("Anton");
        user1.setAge(30);
        user1.setAddress(addressDataSet);
        user1.setPhoneDataSet(phoneDataSet);
        DAOUser daoUserUser =new DAOUserImpl();
        daoUserUser.save(user1);
        //daoUserUser.read(1,User.class);
        System.out.println("------------");
        System.out.println(daoUserUser.read(1,User.class));

    }
}
