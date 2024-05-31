public class ExecutionThread2 extends Thread{
    Integer monitor1;
    int activity_min, activity_max, sleep;

    public ExecutionThread2(Integer monitor1, int activity_min, int activity_max, int sleep) {
        this.monitor1 = monitor1;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        synchronized (monitor1) {
            //while(monitor1 != null) {
                System.out.println(this.getName() + " - STATE 2");
                int k2 = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k2 * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(this.getName() + " - STATE 3");
                System.out.println(this.getName() + " - STATE 4");
            //}
        }
    }
}
