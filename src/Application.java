public class Application {
    public static void main(String[] args){
        Fifo f1 = new Fifo();
        Producer p1 = new Producer("prod-1", 1000, f1);
        Producer p2 = new Producer("prod-2", 1000, f1);
        Producer p3 = new Producer("prod-3", 1000, f1);
        Thread prod1 = new Thread(p1);
        Thread prod2 = new Thread(p2);
        Thread prod3 = new Thread(p3);
        Consumer c1 = new Consumer("con-1", 100, f1);
        Consumer c2 = new Consumer("con-2", 100, f1);
        Consumer c3 = new Consumer("con-3", 100, f1);
        Consumer c4 = new Consumer("con-4", 100, f1);

        prod1.start();
        prod2.start();
        prod3.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
