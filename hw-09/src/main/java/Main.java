import ru.otus.homework.Account;
import ru.otus.homework.JdbcTemplate;
import ru.otus.homework.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        JdbcTemplate template=new JdbcTemplate();

        template.create(User.class);
        template.create(Account.class);

        template.update(new User( 1,"Антон",15));
        template.update(new User(2,"Иван",25));
        template.update(new User(3,"Илья",23));

        template.update(new Account(1,"Users",2));
        template.update(new Account(2,"Admin",1));
        template.update(new Account(3,"Users",1));




        System.out.println(template.load(2,User.class));
        System.out.println(template.load(2,Account.class));
    }
}
