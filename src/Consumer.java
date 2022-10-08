public class Consumer extends Thread {
    private final String name;
    private final int delay;
    private final Fifo fifo;

    public Consumer(String s, int n, Fifo f) {
        this.name = s;
        this.delay = n;
        this.fifo = f;
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                String read = fifo.get();
                System.out.println("consumed " + name + " " + read + " " + System.currentTimeMillis() % 100000);

                //noinspection BusyWait
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("failed: " + name);
            }
        }
    }
}
