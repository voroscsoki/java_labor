import java.io.*;
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
                    case "rm" -> rm(cmd);
                    case "mkdir" -> mkdir(cmd);
                    case "cp" -> cp(cmd);
                    case "head" -> head(cmd);
                    case "mv" -> mv(cmd);
                    case "cat" -> cat(cmd);
                    case "length" -> length(cmd);
                    case "tail" -> tail(cmd);
                    case "wc" -> wc(cmd);
                    case "grep" -> grep(cmd);
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
        targetFolder.ifPresent(file -> wd = file);
    }

    protected static void rm(String[] cmd) throws IOException {
        String targetName = cmd[1];
        if(targetName == null) throw new IllegalArgumentException("Nem lett célpont megadva!");
        File[] files = wd.listFiles();
        if(files == null) throw new FileNotFoundException("Nincsenek fájlok!");
        var target = Arrays.stream(files).filter(f -> f.getName().equals(cmd[1])).findFirst();
        if(target.isEmpty()) throw new FileNotFoundException("Ez a fájl nem létezik!");
        if(!target.get().delete()) throw new IOException("A törlés nem sikerült!");
    }
    protected static void mkdir(String[] cmd) throws IOException {
        String targetName = cmd[1];
        if(targetName == null) throw new IllegalArgumentException("Nem lett célpont megadva!");
        File target = new File(wd.getCanonicalPath() + '/' + targetName);
        if(!target.mkdir()) throw new IOException("Sikertelen mappakészítés!");
    }

    protected static void cp(String[] cmd) throws IOException {
        File f1 = new File(wd.getCanonicalPath() + '/' + cmd[1]);
        File f2 = new File(wd.getCanonicalPath() + '/' + cmd[2]);
        if(!f1.exists() || !f1.isFile()) throw new IllegalArgumentException("A forrásfájl nem létezik!");
        try(FileInputStream from = new FileInputStream(f1);
            FileOutputStream to = new FileOutputStream(f2)){
            to.write(from.readAllBytes());
        }


    }
    protected static void head(String[] cmd) throws IOException {
        int number = 10;
        if(cmd.length < 2) throw new IOException("Nincs elég argumentum");
        if(Objects.equals(cmd[1], "-n")){
            if(cmd.length < 4) throw new IOException("Nincs elég argumentum");
            number = Integer.parseInt(cmd[2]);
        }
        try(BufferedReader bfr = new BufferedReader(new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1]))){
            for (int i = 0; i < number; i++){
                String local = bfr.readLine();
                if(local != null) System.out.println(local);
            }
        }
    }
    protected static void mv(String[] cmd) throws IOException {
        File f1 = new File(wd.getCanonicalPath() + '/' + cmd[1]);
        File f2 = new File(wd.getCanonicalPath() + '/' + cmd[2]);
        if(!f1.exists() || !f1.isFile()) throw new IllegalArgumentException("A forrásfájl nem létezik!");
        try(FileInputStream from = new FileInputStream(f1);
            FileOutputStream to = new FileOutputStream(f2)){
            to.write(from.readAllBytes());
        }
        if(!f1.delete()) throw new IOException("Mozgatásnál törlés hiba!");
    }
    protected static void cat(String[] cmd) throws IOException {
        if(cmd.length < 2) throw new IOException("Nincs elég argumentum");
        try(BufferedReader bfr = new BufferedReader(new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1]))){
            while(true){
                String local = bfr.readLine();
                if(local != null) System.out.println(local);
                else break;
            }
        }
    }
    protected static void length(String[] cmd) throws IOException{
        if(cmd.length < 2) throw new IOException("Nincs elég argumentum");
        try(FileReader fr = new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1])){
            int count = 0;
            while(fr.read() != -1) count++;
            System.out.println(count + " karakter");
        }
    }
    protected static void tail(String[] cmd) throws IOException {
        int number = 10;
        if(cmd.length < 2) throw new IOException("Nincs elég argumentum");
        if(Objects.equals(cmd[1], "-n")){
            if(cmd.length < 4) throw new IOException("Nincs elég argumentum");
            number = Integer.parseInt(cmd[2]);
        }
        try(BufferedReader bfr = new BufferedReader(new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1]))){
            LinkedList lines = new LinkedList<String>();
            while(true){
                String local = bfr.readLine();
                if(local == null) break;
                lines.add(local);
            }
            if(number >= lines.size()) number = lines.size();
            for (int i = lines.size()-number; i < lines.size(); i++)
                System.out.println(lines.get(i));
        }
    }
    protected static void wc(String[] cmd) throws IOException {
        if(cmd.length < 2) throw new IOException("Nincs elég argumentum");
        try(BufferedReader bfr = new BufferedReader(new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1]))){
            int linecount = 0, wordcount = 0, lettercount = 0;
            while(true){
                String local = bfr.readLine();
                if(local != null){
                    linecount++;
                    wordcount += local.split(" ").length;
                    lettercount += local.length();
                }
                else break;
            }
            System.out.println("Sorok száma: " + linecount + ", szavak száma: " + wordcount + ", betűk száma: " + lettercount);
        }
    }
    protected static void grep(String[] cmd) throws IOException { //nem működik
        if(cmd.length < 3) throw new IOException("Nincs elég argumentum");
        try(BufferedReader bfr = new BufferedReader(new FileReader(wd.getCanonicalPath() + '/' + cmd[cmd.length-1]))){
            while(true){
                String local = bfr.readLine();
                if(local == null) break;
                if(local.matches(cmd[1])) System.out.println(local);
            }
        }
    }
}