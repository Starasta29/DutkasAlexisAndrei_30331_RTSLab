package ex2;

class JoinTestThread extends Thread{

    Thread t;

    String n;

    int x;

    static int sum = 0;

    JoinTestThread(String n, Thread t, int x){

        this.setName(n);
        this.n = n;
        this.t = t;
        this.x = x;

    }

    public void run() {

        System.out.println("Thread "+n+" has entered the run() method");

        try {

            if (t != null) t.join();

            System.out.println("Thread "+n+" executing operation.");

            for (int i = 2; i<=x/2; i++)
            {
                if(x%i==0) {
                    System.out.println(i);
                    sum+=i;
                }
            }
            System.out.println("The sum of the divisors is "+sum);
            System.out.println("Thread "+n+" has terminated operation.");

        }

        catch(Exception e){e.printStackTrace();}

    }
}

public class Main {

    public static void main(String[] args){

        int x1 = 50000;
        int x2 = 20000;

        JoinTestThread w1 = new JoinTestThread("Thread 1",null,x1);

        JoinTestThread w2 = new JoinTestThread("Thread 2",w1,x2);

        w1.start();

        w2.start();

    }

}