import dao.DAOUser;
import dao.DAOUserImpl;
import model.User;

public class Main {

    public static void main(String[] args) {
        DAOUser daoUserUser =new DAOUserImpl();
        for (int i = 1; i <100 ; i++) {
            User temp=new User();
            temp.setId(i);
            System.out.println(i);
            daoUserUser.save(temp);
            System.out.println(i);
        }

        for (int i = 1; i <100 ; i++) {
            System.out.println(i+" "+daoUserUser.read(i,User.class));
        }

        for (int i = 1; i <100 ; i++) {
            System.out.println(i+" "+((DAOUserImpl) daoUserUser).readCache(i,User.class));
        }



    }
}
