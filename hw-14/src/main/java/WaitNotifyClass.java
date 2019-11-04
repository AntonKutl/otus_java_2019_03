
public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile String flagTread = "one";

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
            StringBuffer str=new StringBuffer("Поток "+ flag+":");
            try {

                for (int i = 1; i < 11; i++) {
                    Thread.sleep(350);
                    while (!flagTread.equals(flag)) {
                        monitor.wait();
                    }

                    str.append(i+" ");
                    System.out.println(str);
                    if (flag.equals("one"))flagTread = "two";
                    else flagTread = "one";
                    monitor.notify();
                }
                for (int i = 9; i > 0; i--) {

                    Thread.sleep(350);

                    while (!flagTread.equals(flag)) {
                        monitor.wait();
                    }

                    str.append(i+" ");
                    System.out.println(str);
                    if (flag.equals("one"))flagTread = "two";
                    else flagTread = "one";
                    monitor.notify();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

