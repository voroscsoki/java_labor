import java.util.LinkedList;

public class Fifo {

    private final LinkedList<String> strlist = new LinkedList<>();

    public synchronized void put(String s) throws InterruptedException {
        System.out.println("Put " + Thread.currentThread().getId());
        while(strlist.size() >= 10){
            wait();
        }
        strlist.addFirst(s);
        notify();
    }
    public synchronized String get() throws InterruptedException {
        System.out.println("Get " + Thread.currentThread().getId());
        while(strlist.size() == 0){
            wait();
        }
        String out = strlist.getLast();
        strlist.removeLast();
        notify();
        return out;
    }
}
