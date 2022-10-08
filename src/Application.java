public class Application {
    public static void main(String[] args){
        Fifo f1 = new Fifo();
        Producer p1 = new Producer("1-prod", 200, f1);
        Thread prod = new Thread(p1); //"wrapping" runnable object in thread
        Consumer c1 = new Consumer("1-con", 1000, f1); //slow consumer causes bottleneck
        prod.start();
        c1.start();
    }
}
