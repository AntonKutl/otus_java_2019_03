import ru.otus.homework.*;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, SQLException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        JdbcTemplate template = new JdbcTemplateImpl();
        JdbcTemplate template2=new JdbcTemplateImpl();

        template.create("create table User(id bigint(20) NOT NULL auto_increment, name varchar(255),age int(3),rs int(3))");
        template2.create("create table Account(no bigint(20) NOT NULL auto_increment, type varchar(255),rest int(3))");

        template.update(new User(1, "Антон", 15, 22));
        template.update(new User(2, "Иван", 25, 22));
        template.update(new User(3, "Илья", 23, 25));

        template2.update(new Account(1,"Users",2));
        template2.update(new Account(2,"Admin",1));
        template2.update(new Account(3,"Users",1));


        System.out.println(template.load(2, User.class));
        System.out.println(template2.load(2,Account.class));
    }
}
