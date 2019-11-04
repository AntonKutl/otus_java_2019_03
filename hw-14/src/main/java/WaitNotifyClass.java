
public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile String nameTread = "one";// блокировка потока по имени, с boolean чередование не получиться

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread oneThread = new Thread(() -> {
            w.print("one");
        });
        Thread twoThread = new Thread(() -> {
            w.print("two");
        });
        oneThread.start();
        twoThread.start();
    }

    public void print(String flag) {
        synchronized (monitor) {
            StringBuilder str=new StringBuilder("Поток "+ flag+":");
            try {

                for (int i = 1; i < 19; i++) {
                    Thread.sleep(350);
                    while (!nameTread.equals(flag)) {
                        monitor.wait();
                    }

                    str.append(i);
                    System.out.println(str);
                    nameTread = nameTread.equals("one")?"two":"one";
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

