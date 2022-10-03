import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Beer> beers = new ArrayList<>();
    public static void main(String[] args) {
        String line;
        beers.add(new Beer("Guiness", "stout", 4.2));
        beers.add(new Beer("Kőbányai", "ale", 4.3));

        Scanner r = new Scanner(System.in);
        while(true){
            line = r.nextLine();
            String[] cmd = line.split(" ");
            try{
                if ("exit".equals(cmd[0])) {
                    return;
                }
                else if ("add".equals(cmd[0])) {
                    add(cmd);
                }
                else if ("list".equals(cmd[0])) {
                    list(cmd);
                }
            }
            catch(Exception e){
                System.out.println("Hiba! " + e.getMessage());
            }
        }
    }

    protected static void add(String[] cmd) throws IllegalArgumentException {
        if(cmd.length < 4) throw new IllegalArgumentException("Túl kevés paraméter!");
        beers.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
    }
    protected static void list(String[] cmd) throws IllegalArgumentException {
        for (Beer b : beers) {
            System.out.println(b);
        }
    }
}