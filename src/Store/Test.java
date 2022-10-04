package Store;

public class Test {
    public static void main(String[] args){
        PQueue<String> pq = new PQueue<>();
        pq.push("teszt1");
        pq.push("teszt2");
        pq.push("teszt3");
        try{
            System.out.println(pq.top());
            System.out.println(pq.pop());
            System.out.println("Size: " + pq.size());
        }
        catch (EmptyQueueException ignored) {}
        pq.clear();
        System.out.println(pq.size());

        PQueue<Integer> s = new PQueue<Integer>();
        s.push(1); s.push(2); s.push(3); s.push(4);
        for (Object i : s) { //we know it's an integer
            System.out.println(i);
        }
    }
}
