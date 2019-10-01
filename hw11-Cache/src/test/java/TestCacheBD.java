
import cachehw.HwCache;
import cachehw.MyCache;
import dao.DAOUser;
import dao.DAOUserImpl;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DisplayName("MyCache должен")
public class TestCacheBD {
    public static final Logger logger = LoggerFactory.getLogger(TestCacheBD.class);
    @Test
    @DisplayName("работать быстрее  СУБД")
    void testBD()  {
        DAOUser daoUserUser =new DAOUserImpl();
        for (int i = 1; i <100 ; i++) {
            User temp=new User();
            temp.setId(i);
            System.out.println(i);
            daoUserUser.save(temp);
            System.out.println(i);
        }
        logger.info("Измерение времени извлеченя данных из БД");
        long startBD = System.currentTimeMillis();
        for (int i = 1; i <100 ; i++) {
            daoUserUser.read(i,User.class);
        }
        long timeBD=System.currentTimeMillis()-startBD;
        logger.info("Измерение времени извлеченя данных из кеша");
        long startCache = System.currentTimeMillis();
        for (int i = 1; i <100 ; i++) {
            ((DAOUserImpl) daoUserUser).readCache(i,User.class);
        }
        long timeCache=System.currentTimeMillis()-startCache;
        logger.info("Кэш быстрее на:{}",timeBD-timeCache);

        Assertions.assertTrue(timeBD>timeCache);
    }

}
