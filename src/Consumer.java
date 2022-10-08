public class Consumer extends Thread {
    private final String s;
    private final int n;
    private final Fifo f;

    public Consumer(String s, int n, Fifo f) {
        this.s = s;
        this.n = n;
        this.f = f;
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                String read = f.get();
                System.out.println("consumed " + s + " " + read + " " + System.currentTimeMillis() % 100000);

                //noinspection BusyWait
                Thread.sleep(n);
            } catch (InterruptedException e) {
                System.out.println("failed: " + s);
            }
        }
    }
}
