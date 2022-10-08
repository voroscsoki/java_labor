public class Producer implements Runnable {
    private final String text;
    private final int delay;
    private final Fifo fifo;
    private int counter = 0;

    public Producer(String text, int d, Fifo f) {
        this.text = text;
        this.delay = d;
        this.fifo = f;
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                fifo.put(text + " " + counter);
                System.out.println("produced " + text + " " + (counter++) + " " + System.currentTimeMillis() % 100000);

                //noinspection BusyWait
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("failed: " + text);
            }
        }
    }
}
