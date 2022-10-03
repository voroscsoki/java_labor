import Comparators.NameComparator;
import Comparators.StrengthComparator;
import Comparators.StyleComparator;
import Data.Beer;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static ArrayList<Beer> beers = new ArrayList<>();
    public static void main(String[] args) {
        String line;
        beers.add(new Beer("Guinness", "stout", 4.2));
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
                else if ("load".equals(cmd[0])) {
                    load(cmd);
                }
                else if ("save".equals(cmd[0])) {
                    save(cmd);
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
    protected static void list(String[] cmd) {
        ArrayList<Beer> local = (ArrayList<Beer>) beers.clone();
        Comparator<Beer> comp;
        if(cmd.length > 1 && cmd[1] != null)
            switch (cmd[1]){
                case "name":{
                    comp = new NameComparator();
                    local.sort(comp);
                    break;
                }
                case "style": {
                    comp = new StyleComparator();
                    local.sort(comp);
                    break;
                }
                case "strength": {
                    comp = new StrengthComparator();
                    local.sort(comp);
                    break;
                }
            }
        else local = beers;
        for (Beer b : local) {
            System.out.println(b);
        }
    }
    protected static void load(String[] cmd) throws IOException, ClassNotFoundException {
        if(cmd.length < 2) throw new IllegalArgumentException("Túl kevés paraméter!");
        File inFile = new File(cmd[1]);
        if (!inFile.exists()) throw new IOException("Nincs ilyen fájl!");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));
        beers = (ArrayList<Beer>) in.readObject();
    }
    protected static void save(String[] cmd) throws IOException {
        if(cmd.length < 2) throw new IllegalArgumentException("Túl kevés paraméter!");
        File outFile = new File(cmd[1]);
        if(!outFile.createNewFile()) throw new IOException("Fájlkészítés hiba!");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
        out.writeObject(beers);
    }
}