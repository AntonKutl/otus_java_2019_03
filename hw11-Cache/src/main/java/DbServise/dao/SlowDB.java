package DbServise.dao;

import cachehw.HWCacheDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class SlowDB<K, V> {
    private final Map<K, V> bd = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(HWCacheDemo.class);

    public void save(K key, V value) {
        bd.put(key, value);
        logger.info("Сохранение в БД ключас:{}, с значением:{}",key, value);
    }

    public V get(K key) throws InterruptedException {
        Thread.sleep(50);
        logger.info("Загрузка из БД ключас:{}, с значением:{}",key, bd.get(key));
        return bd.get(key);
    }

}
