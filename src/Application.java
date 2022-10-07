public class Application {
    public static void main(String[] args){
        try {
            Producer p = new Producer("teszt");
            p.go();
        }
        catch (Exception e) {/*unhandled*/ }
    }
}
