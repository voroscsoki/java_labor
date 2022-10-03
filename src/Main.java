import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Beer gui = new Beer("Guiness", "stout", 4.2);
        Beer kobi = new Beer("Kőbányai", "ale", 4.3);
        System.out.println(gui);
        System.out.println(kobi);

        String line;
        Scanner r = new Scanner(System.in);
        while(true){
            line = r.nextLine();
            String[] words = line.split(" ");
            if(words[0].equals("exit")) { return; }
        }
    }
}