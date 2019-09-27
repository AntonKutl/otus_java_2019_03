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
        PhoneDataSet phoneDataSet1=new PhoneDataSet();
        PhoneDataSet phoneDataSet2=new PhoneDataSet();
        phoneDataSet1.setNumber("11111");
        phoneDataSet2.setNumber("22222");
        addressDataSet.setStreet("Ленина");
        user1.setId(1);
        user1.setName("Anton");
        user1.setAge(30);
        user1.setAddress(addressDataSet);
        user1.setPhoneDataSet(phoneDataSet1);
        user1.setPhoneDataSet(phoneDataSet2);
        DAOUser daoUserUser =new DAOUserImpl();
        daoUserUser.save(user1);
        System.out.println("------------");
        System.out.println(daoUserUser.read(1,User.class));


    }
}
