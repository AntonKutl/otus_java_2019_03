import DbServise.dao.SlowDataSrc;
import cachehw.HwCache;
import cachehw.HwListener;
import cachehw.MyCache;
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
    void testBD() throws InterruptedException {
        SlowDataSrc db=new SlowDataSrc();
        HwCache<Integer, User> cache = new MyCache<>();
        HwListener<Integer, User> listener =
                (key, value, action) -> logger.info("key:{}, value:{}, action: {}",  key, value, action);
        cache.addListener(listener);
        for (int i = 0; i <100; i++) {
            cache.put(i,new User());
            db.save(i,new User());
        }
        logger.info("Измерение времени извлеченя данных из кеша");
        long startCache = System.currentTimeMillis();
        for (int i = 0; i <100 ; i++) {
            cache.get(i);
        }
        long timeCache=System.currentTimeMillis()-startCache;


        logger.info("Измерение времени извлеченя данных из БД");
        long startBD = System.currentTimeMillis();
        for (int i = 0; i <100 ; i++) {
            db.get(i);;
        }
        long timeBD=System.currentTimeMillis()-startBD;
        Assertions.assertTrue(timeBD>timeCache);
    }

}
