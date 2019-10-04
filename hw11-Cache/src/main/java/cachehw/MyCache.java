package cachehw;
import java.lang.ref.WeakReference;
import java.util.*;


/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
    private final List<WeakReference<HwListener>> listListener = new ArrayList<>();
    private final Map<K, V> mapCache = new WeakHashMap<>();
    public void put(K key, V value) {
        mapCache.put(key, value);
        listListener.stream().forEach((s) -> s.get().notify(key, value, "put"));
    }

    public void remove(K key) {
        listListener.stream().forEach((s) -> s.get().notify(key, mapCache.get(key), "remove"));
        mapCache.remove(key);
    }

    public V get(K key) {
        V value=mapCache.get(key);
        listListener.stream().forEach((s) -> s.get().notify(key, mapCache.get(key), "get"));
        return value;
    }

    public int size() {
        return mapCache.size();
    }

    public boolean isKey(K key){
        return mapCache.containsKey(key);
    }

    public void addListener(HwListener listener) {
        this.listListener.add(new WeakReference<>(listener));
    }

    public void removeListener(HwListener listener) {
        this.listListener.remove(listener);
    }

}
