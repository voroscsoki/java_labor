public class Application {
    public static void main(String[] args){
        Fifo teszt = new Fifo();
        Producer p1 = new Producer("elso", teszt);
        Producer p2 = new Producer("masodik", teszt);
        try {
            p1.start();
            Thread.sleep(500);
            p2.start();
        } catch (InterruptedException e) {
            //unhandled
        }
    }
}
