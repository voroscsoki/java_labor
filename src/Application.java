public class Application {
    public static void main(String[] args){
        Producer p1 = new Producer("elso");
        Producer p2 = new Producer("masodik");
        try {
            p1.start();
            Thread.sleep(500);
            p2.start();
        } catch (InterruptedException e) {
            //unhandled
        }
    }
}
