package homework;

public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile int numberTread = 1;// блокировка потока по имени, с boolean чередование не получиться
    private static final int STOP_TIME=350;

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread oneThread = new Thread(() -> {
            w.print(1);
        });
        Thread twoThread = new Thread(() -> {
            w.print(2);
        });
        oneThread.start();
        twoThread.start();
    }

    public void print(int number) {
        synchronized (monitor) {
            StringBuilder str=new StringBuilder("Поток "+ number+":");
            try {
                int count=0;

                for (int i = 1; i < 20; i++) {
                    Thread.sleep(STOP_TIME);
                    while (!(numberTread ==number)) {
                        monitor.wait();
                    }
                    count=i<11?i:count-1;
                    str.append(count);
                    System.out.println(str);
                    numberTread = numberTread==1?2:1;
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

