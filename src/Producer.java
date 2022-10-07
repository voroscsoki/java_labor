public class Producer extends Thread {
    private final String text;

    public Producer(String text) {
        this.text = text;
    }

    public void run() {
        int i = 0;

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println(text + " " + (++i) + " " + System.currentTimeMillis() % 100000);
            try {
                //noinspection BusyWait
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
