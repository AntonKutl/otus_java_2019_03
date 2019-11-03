
public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile String flagTread = "one";

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread oneThread = new Thread(() -> {
            w.printOne();
        });
        Thread twoThread = new Thread(() -> {
            w.printTwo();
        });
        oneThread.start();
        twoThread.start();
    }

    public void printOne() {
        synchronized (monitor) {
            StringBuffer str=new StringBuffer("Поток 1:");
            try {
                for (int i = 1; i < 11; i++) {
                    while (!flagTread.equals("one")) {
                        monitor.wait();
                    }

                    System.out.print('\r');

                    Thread.sleep(50);

                    str.append(i+" ");
                    System.out.print(str);
                    flagTread = "two";
                    monitor.notify();
                }
                for (int i = 9; i > 0; i--) {
                    while (!flagTread.equals("one")) {
                        monitor.wait();
                    }

                    System.out.print('\r');

                    Thread.sleep(150);

                    str.append(i+" ");
                    System.out.print(str);
                    flagTread = "two";
                    monitor.notify();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printTwo() {

        synchronized (monitor) {
            StringBuffer str=new StringBuffer("Поток 2:");
            try {
                for (int i = 1; i < 11; i++) {
                    while (!flagTread.equals("two")) {
                        monitor.wait();
                    }

                    Thread.sleep(150);

                    str.append(i+" ");
                    System.out.print(str);
                    flagTread= "one";
                    monitor.notify();
                }
                for (int i = 9; i > 0; i--) {
                    while (!flagTread.equals("two")) {
                        monitor.wait();
                    }

                    Thread.sleep(100);

                    str.append(i+" ");
                    System.out.print(str);
                    flagTread= "one";
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

