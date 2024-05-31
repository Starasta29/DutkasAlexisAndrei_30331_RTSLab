import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class ExecutionThread_2 extends Thread {
    Semaphore sem1, sem2;
    CyclicBarrier barrier;
    int sleep, activity_min1, activity_max1, activity_min2, activity_max2;

    public ExecutionThread_2(Semaphore sem1, Semaphore sem2, CyclicBarrier barrier, int sleep, int activity_min1,
                             int activity_max1, int activity_min2, int activity_max2) {
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.barrier = barrier;
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
        try {
            sem1.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(this.getName() + " - STATE 2");

        k = (int) Math.round(Math.random() * (activity_max2
                - activity_min2) + activity_min2);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        if (sem2.tryAcquire(1)) {
            try {
                System.out.println(this.getName() + " - STATE 3");
                Thread.sleep(Math.round(sleep * 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                sem2.release();
            }
        }
        sem1.release();
        System.out.println(this.getName() + " - STATE 4");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        CyclicBarrier barrier = new CyclicBarrier(2);

        while (true) {
            new ExecutionThread_2(sem1, sem2, barrier, 4, 2, 4, 4, 6).start();
            new ExecutionThread_2(sem2, sem1, barrier, 5, 3, 5, 5, 7).start();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
