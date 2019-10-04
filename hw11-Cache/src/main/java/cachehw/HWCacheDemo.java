package cachehw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class HWCacheDemo {
    private static final Logger logger = LoggerFactory.getLogger(HWCacheDemo.class);

    public static void main(String[] args) throws InterruptedException {
        new HWCacheDemo().demo();
    }

    private void demo() throws InterruptedException {
        HwCache<Integer, Integer> cache = new MyCache<>();
        HwListener<Integer, Integer> listener =
                (key, value, action) -> logger.info("key:{}, value:{}, action: {}",  key, value, action);
        cache.addListener(listener);
        cache.put(1,10);
        logger.info("getValue:{}", cache.get(1));
        System.out.println("GC");
        System.gc();
        Thread.sleep(1000);
        logger.info(String.valueOf(((MyCache<Integer, Integer>) cache).size()));
        cache.remove(1);
        cache.removeListener(listener);
    }
}
