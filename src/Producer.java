public class Producer extends Thread {
    private final String text;
    private final Fifo fifo;
    private int counter = 0;

    public Producer(String text, Fifo f) {
        this.text = text;
        this.fifo = f;
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                fifo.put(text + " " + counter);
                System.out.println("produced " + text + " " + (counter++) + " " + System.currentTimeMillis() % 100000);

                //noinspection BusyWait
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("failed: " + text);
            }
        }
    }
}
