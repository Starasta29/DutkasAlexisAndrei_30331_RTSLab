import java.util.concurrent.Semaphore;

class Fir extends Thread {
    int name, delay, k, permit;
    Semaphore s;

    Fir(int n, Semaphore sa, int delay, int k, int permit) {
        this.name = n;
        this.s = sa;
        this.delay = delay;
        this.k = k;
        this.permit = permit;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Fir " + name + " State 1");
                this.s.acquire(this.permit); // critical region
                System.out.println("Fir " + name + " took a token from the semaphore");

                System.out.println("Fir " + name + " State 2");
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                this.s.release(); // end of critical region
                System.out.println("Fir " + name + " released a token from the semaphore");

                System.out.println("Fir " + name + " State 4");
                Thread.sleep(this.delay * 500);

                System.out.println("Fir " + name + " State 5");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Semaphore s = new Semaphore(2);
        Fir f1, f2, f3;

        f1 = new Fir(1, s, 5, (int) Math.round(Math.random() * 3 + 3), 1);
        f2 = new Fir(2, s, 3, (int) Math.round(Math.random() * 3 + 4), 1);
        f3 = new Fir(3, s, 6, (int) Math.round(Math.random() * 2 + 5), 1);

        f1.start();
        f2.start();
        f3.start();
    }

}