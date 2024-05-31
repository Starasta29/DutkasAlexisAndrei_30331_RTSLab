import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ExecutionThread2 extends Thread {
    Lock l1, l2;
    int sleep, activity_min1, activity_max1, activity_min2, activity_max2;

    public ExecutionThread2(Lock l1, Lock l2, int sleep, int activity_min1,
                            int activity_max1, int activity_min2, int activity_max2) {
        this.l1 = l1;
        this.l2 = l2;
        this.sleep = sleep;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;

        this.activity_min2 = activity_min2;
        this.activity_max2 = activity_max2;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max1
                - activity_min1) + activity_min1);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        this.l1.tryLock();
        System.out.println(this.getName() + " - STATE 2");

        k = (int) Math.round(Math.random() * (activity_max2
                - activity_min2) + activity_min2);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if (l2.tryLock()) {
            System.out.println(this.getName() + " - STATE 3");
            try {
                Thread.sleep(Math.round(sleep * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
        l2.unlock();
        l1.unlock();


        System.out.println(this.getName() + " - STATE 4");
    }
}

public class Main {
    public static void main(String[] args) {

        Lock l1 = new ReentrantLock();
        Lock l2 = new ReentrantLock();

        new ExecutionThread2(l1, l2, 4, 2, 4, 4, 6).start();
        new ExecutionThread2(l2, l1, 5, 3, 5, 5, 7).start();
    }
}
