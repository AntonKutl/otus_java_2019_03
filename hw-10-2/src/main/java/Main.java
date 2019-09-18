import ru.otus.homework.dao.DAOUser;
import ru.otus.homework.dao.DAOUserImpl;
import ru.otus.homework.model.AddressDataSet;
import ru.otus.homework.model.PhoneDataSet;
import ru.otus.homework.model.User;

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
        DAOUser DAOUser =new DAOUserImpl();
        DAOUser.create(user1);
        DAOUser.update(user1);
        System.out.println(DAOUser.load(1,user1.getClass()));

    }
}
