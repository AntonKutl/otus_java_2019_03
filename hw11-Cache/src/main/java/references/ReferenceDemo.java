package references;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 * <p>
 * VM options: -Xmx256m -Xms256m
 */
public class ReferenceDemo {
  private static final Logger logger = LoggerFactory.getLogger(ReferenceDemo.class);

  public static void main(String[] args) throws InterruptedException {
    //strong();
    //  weak();
     // soft();
     // phantom();
  }

  private static void strong() {
    int size = 1010;
    List<BigObject> references = new ArrayList<>(size);

    for (int k = 0; k < size; k++) {
      references.add(new BigObject());
      logger.info("k:{}", k);
    }
    //OutOfMemoryError for -Xmx256m -Xms256m
    logger.info("Size: {}", references.size());
  }

  private static void weak() {
    int size = 58;
    List<WeakReference<BigObject>> references = new ArrayList<>(size);

    for (int k = 0; k < size; k++) {
      references.add(new WeakReference<>(new BigObject()));
    }

    //Если раскоментировать, то gc удалит все объекты
    //System.gc();

    int sum = 0;
    for (int k = 0; k < size; k++) {
      if (references.get(k).get() != null) {
        sum++;
      }
    }

    logger.info("Weak references: {}", sum);
  }

  private static void soft() {
    int size = 1000;
    List<SoftReference<BigObject>> references = new ArrayList<>(size);

    for (int k = 0; k < size; k++) {
      references.add(new SoftReference<>(new BigObject()));
    }

   // System.gc();

    int sum = 0;
    for (int k = 0; k < size; k++) {
      if (references.get(k).get() != null) {
        sum++;
      }
    }

    logger.info("Soft references: {}", sum);
  }


  private static void phantom() throws InterruptedException {
    BigObject a = new BigObject();
    logger.info("a: {}", a);

    //создаем очередь ReferenceQueue
    ReferenceQueue<BigObject> refQueue = new ReferenceQueue<>();

    //создаем Phantom Reference на объект типа BigObject и "подвязываем" ее на переменную a.
    PhantomReference<BigObject> phantomA = new PhantomReference<>(a, refQueue);
    logger.info("Ref in pool before GC: {}", refQueue.poll());

    a = null;
    //теперь объект a может быть удален сборщиком мусора.
    //До того, как gc сработает в refQueue будет null.

    a = phantomA.get();    //всегда null

    System.gc();
    Thread.sleep(100);
    logger.info("Ref in pool after GC: {}", refQueue.poll());

    Reference<? extends BigObject> aa = refQueue.poll();
    logger.info("a: {}", aa);

  }

  static class BigObject {
    final byte[] array = new byte[1024 * 1024];

    public byte[] getArray() {
      return array;
    }
  }

}
