import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true){
            String[] cmd = s.nextLine().split(" ");

            if (cmd[0].equals("exit")) {
                exit(cmd);
            }
            else if (cmd[0].equals("hello")) {
                hello(cmd);
            }
        }
    }

    protected static void exit(String[] cmd){
        System.exit(0);
    }
    protected static void hello(String[] cmd){
        System.out.println("Hello world!");
    }
}