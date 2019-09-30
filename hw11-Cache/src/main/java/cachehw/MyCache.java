package cachehw;
import java.lang.ref.WeakReference;
import java.util.*;


/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
    List<WeakReference<HwListener>> listListener = new ArrayList<>();
    Map<K, V> mapCache = new WeakHashMap<>();
    public void put(K key, V value) {
        mapCache.put((K) new WeakReference<>(key), value);
        listListener.stream().forEach((s) -> s.get().notify(key, value, "put"));
    }

    public void remove(K key) {
        Set<WeakReference> set= (Set<WeakReference>) mapCache.keySet();
        for (WeakReference temp:set) {
            if (temp.get()==key){
                listListener.stream().forEach((s) -> s.get().notify(key, mapCache.get(temp), "remove"));
                mapCache.remove(temp);
                break;
            }
        }
    }

    public V get(K key) {
        Set<WeakReference> set= (Set<WeakReference>) mapCache.keySet();
        V value = null;
        for (WeakReference temp:set) {
            if (temp.get()==key){

                value=mapCache.get(temp);
                listListener.stream().forEach((s) -> s.get().notify(key, mapCache.get(temp), "get"));
            }
        }
        return value;
    }

    public int size() {
        return mapCache.size();
    }

    public void addListener(HwListener listener) {
        this.listListener.add(new WeakReference<>(listener));
    }

    public void removeListener(HwListener listener) {
        this.listListener.remove(listener);
    }

}
