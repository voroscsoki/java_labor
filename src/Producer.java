public class Producer {
    private final String text;

    public Producer(String text) {
        this.text = text;
    }

    void go() throws InterruptedException {
        int i = 0;
        while (true) {
            System.out.println(text + " " + (++i) + " " + System.currentTimeMillis() % 100000);
            Thread.sleep(1000);
        }
    }
}
