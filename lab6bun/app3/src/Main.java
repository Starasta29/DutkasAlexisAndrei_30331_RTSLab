import java.util.concurrent.CountDownLatch;

class ExecutionThread_app3 extends Thread {

    CountDownLatch latch;
    int sleep, activity_min, activity_max;

    Integer monitor1, monitor2;
    public ExecutionThread_app3(Integer monitor1,Integer monitor2,CountDownLatch latch,int sleep, int activity_min, int activity_max)
    {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;

        this.latch=latch;

        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;

    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(sleep * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + " - STATE 2");

        int k = (int) Math.round(Math.random() * (activity_max
                - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;

        }
        synchronized (monitor1) {
            monitor1.notify();
        }
        synchronized (monitor2){
            monitor2.notify();
        }

        System.out.println(this.getName() + " - STATE 3");

        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class ExecutionThread_app3_2 extends Thread {
    Integer monitor;
    CountDownLatch latch;
    int activity_min, activity_max,sleep;
    public ExecutionThread_app3_2(Integer monitor,CountDownLatch latch,int activity_min, int activity_max,int sleep) {
        this.monitor = monitor;

        this.latch=latch;

        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep=sleep;
    }
    public void run() {

            System.out.println(this.getName() + " - STATE 1");
            try {
                Thread.sleep(sleep * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (activity_max
                    - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;

            }

        System.out.println(this.getName() + " - STATE 3");

        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
public class Main {
    public static void main(String[] args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(2);

        CountDownLatch latch = new CountDownLatch(3);

        new ExecutionThread_app3(monitor1,monitor2,latch,7, 2, 3).start();


        int x = 10;
        int y;
        y=x;

        new ExecutionThread_app3_2(monitor1,latch,3, 5,x).start();
        new ExecutionThread_app3_2( monitor2,latch,4, 6,y).start();

    }
}