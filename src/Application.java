public class Application {
    public static void main(String[] args){
        Fifo f1 = new Fifo();
        Producer p1 = new Producer("1-prod", f1);
        Consumer c1 = new Consumer("1-con", 1000, f1);
        p1.start();
        c1.start();
    }
}
