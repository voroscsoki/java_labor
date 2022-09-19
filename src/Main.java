import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
                    case "cd" -> cd(cmd);
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
    protected static void pwd(String[] cmd) throws IOException {
        String current = wd.getCanonicalPath(); //throws
        File currentFolder = new File(current);
        System.out.println("Jelenlegi mappa: " + current);
        System.out.println("Fájlok és almappák száma: " + Objects.requireNonNull(currentFolder.listFiles()).length);
    }
    protected static void ls(String[] cmd) throws FileNotFoundException {
        boolean detailed = cmd.length > 1 && cmd[1].equals("-l");
        if(wd == null){
            throw new FileNotFoundException("Nem létező mappa!");
        }
        File[] list = wd.listFiles();
        if(list == null){
            throw new FileNotFoundException("Érvénytelen mappa!");
        }
        Arrays.sort(list, Comparator.comparing(File::getName)); //masszívan felesleges
        for (File file: list) {
            if(detailed)
                System.out.println((file.isDirectory()? "D\t" : "F\t") + file.getName() + " -- " + fileOrDirSize(file));
            else
                System.out.println(file.getName());
        }
    }
    protected static long fileOrDirSize(File f){
        long length = 0;
        if(f.isFile()) return f.length();

        File[] fileList = f.listFiles();
        if(fileList == null) return 0;

        for (File file : fileList){
            if(file.isFile()) length += file.length();
            else length += fileOrDirSize(file);
        }
        return length;
    }

    protected static void cd(String[] cmd) throws IOException {
        String target = cmd[1];
        if(target.equals("..")){
            File next = wd.getParentFile();
            if(next != null) wd = next;
            return;
        }
        File[] files = wd.listFiles();
        if(files == null) throw new IOException("Fájllistázás nem sikerült!");
        Optional<File> targetFolder = Arrays.stream(files)
            .filter(f -> f.isDirectory() && f.getName().equals(target))
            .findFirst();
        if(targetFolder.isPresent()) wd = targetFolder.get();
    }
}