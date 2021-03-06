package test;

import cachehw.HwCache;
import cachehw.HwListener;
import cachehw.MyCache;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@DisplayName("MyCache должен")
public class TestCache {
    public static final Logger logger = LoggerFactory.getLogger(TestCache.class);

    @Test
    @DisplayName("должен сбрасываться при запуске GC")
     void testGC() throws InterruptedException {
        HwCache<String, User> cache = new MyCache<>();
        HwListener<String, User> listener =
                (key, value, action) -> logger.info("key:{}, value:{}, action: {}",  key, value, action);
        cache.addListener(listener);
        for (int i = 0; i <100; i++) {
            cache.put(String.valueOf(i),new User());
        }
        System.gc();
        Thread.sleep(100);
        Assertions.assertEquals(0, ((MyCache<String, User>) cache).size());
    }
}
