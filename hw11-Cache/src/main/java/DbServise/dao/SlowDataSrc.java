package DbServise.dao;

import cachehw.HWCacheDemo;
import cachehw.HwListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class SlowDataSrc<K, V> {
    List<WeakReference<HwListener>> listListener = new ArrayList<>();
    Map<K, V> mapCache = new WeakHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(HWCacheDemo.class);

    public void save(K key, V value) {
        mapCache.put((K) new WeakReference<>(key), value);
        logger.info("Сохранение в БД:{}",key, value);
    }


    public V get(K key) throws InterruptedException {
        Set<WeakReference> set= (Set<WeakReference>) mapCache.keySet();
        V value = null;
        for (WeakReference temp:set) {
            if (temp.get()==key){

                value=mapCache.get(temp);
                logger.info("Загрузка из БД:{}",key, value);
            }
        }
        Thread.sleep(50);

        return value;
    }

}
