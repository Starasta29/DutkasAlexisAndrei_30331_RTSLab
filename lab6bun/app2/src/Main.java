import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

class ExecutionThread_app2 extends Thread {
    ReentrantLock lock;
    CountDownLatch latch;
    int sleep, activity_min, activity_max;
    public ExecutionThread_app2(ReentrantLock lock,CountDownLatch latch, int sleep, int activity_min, int activity_max) {
        this.lock = lock;
        this.latch=latch;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }
    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            lock.lock();

            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (activity_max
                    - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(Math.round(sleep * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println((this.getName()) + " Monitor released");
            lock.unlock();

            System.out.println(this.getName() + " - STATE 3");

            try {
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class ExecutionThread2_app2 extends Thread {
    ReentrantLock lock1, lock2;
    CountDownLatch latch;
    int sleep, activity_min, activity_max;
    public ExecutionThread2_app2(ReentrantLock lock1, ReentrantLock lock2,CountDownLatch latch, int sleep, int activity_min, int activity_max) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.latch=latch;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }
    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            lock1.lock();

            lock2.lock();

            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (activity_max
                    - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(Math.round(sleep * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println((this.getName()) + " Monitor released");

            lock2.unlock();

            lock1.unlock();
            System.out.println(this.getName() + " - STATE 3");

            try {
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        CountDownLatch latch;

        while(true) {
            latch = new CountDownLatch(4);
            new ExecutionThread_app2(lock1, latch, 4, 2, 4).start();
            new ExecutionThread_app2(lock2, latch, 3, 3, 6).start();
            new ExecutionThread2_app2(lock1, lock2, latch, 5, 2, 5).start();
            try {
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
