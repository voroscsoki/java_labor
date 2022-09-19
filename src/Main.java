import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static File wd = new File(System.getProperty("user.dir"));
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true){
            String[] cmd = s.nextLine().split(" ");
            try{
                switch (cmd[0]) {
                    case "exit" -> exit(cmd);
                    case "hello" -> hello(cmd);
                    case "pwd" -> pwd(cmd);
                    case "ls" -> ls(cmd);
                }
            }
            catch (Exception e){
                System.out.println("Hiba: " + e.getMessage());
            }
        }
    }

    protected static void exit(String[] cmd){
        System.exit(0);
    }
    protected static void hello(String[] cmd){ System.out.println("Hello world!"); }
    protected static void pwd(String[] cmd) throws FileNotFoundException {
        String current = System.getProperty("user.dir");
        if(current == null) {
            throw new FileNotFoundException("Nem létező mappa!");
        }
        File currentFolder = new File(current);
        System.out.println("Jelenlegi mappa: " + current);
        System.out.println("Fájlok és almappák száma: " + Objects.requireNonNull(currentFolder.listFiles()).length);
    }
    protected static void ls(String[] cmd) throws FileNotFoundException {
        if(wd == null){
            throw new FileNotFoundException("Nem létező mappa!");
        }
        File[] list = wd.listFiles();
        if(list == null){
            throw new FileNotFoundException("Érvénytelen mappa!");
        }
        Arrays.sort(list, Comparator.comparing(File::getName)); //masszívan felesleges
        for (File file: list) {
            System.out.println(((file.isDirectory()) ? "M\t" : "F\t") + file.getName());
        }
    }
}