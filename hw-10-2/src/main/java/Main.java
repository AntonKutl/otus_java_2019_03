import ru.otus.homework.*;

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
        JdbcTemplate jdbcTemplate=new JdbcTemplateImpl();
        jdbcTemplate.create(user1);
        jdbcTemplate.update(user1);
        jdbcTemplate.load(1,user1.getClass());

    }
}
