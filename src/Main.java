import Data.Beer;

import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Beer> beers = new ArrayList<>();
    private static final HashMap<String, Comparator<Beer>> comps = new HashMap<>();
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final List<String> lparams;

    static {
        beers.add(new Beer("Guinness", "stout", 4.2));
        beers.add(new Beer("Kőbányai", "ale", 4.3));

        commands.put("add", Main::add);
        commands.put("list", Main::list);
        commands.put("load", Main::load);
        commands.put("save", Main::save);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("delete", Main::delete);

        comps.put("name", Comparator.comparing(Beer::getName));
        comps.put("style", Comparator.comparing(Beer::getStyle));
        comps.put("strength", Comparator.comparing(Beer::getStrength));

        lparams = new LinkedList<>();
        lparams.addAll(comps.keySet());
    }

    public static void main(String[] args) {

        String line;
        Scanner r = new Scanner(System.in);
        while(true){
            line = r.nextLine();
            String[] cmd = line.split(" ");
            try{
                if ("exit".equals(cmd[0])) {
                    return;
                }
                Command attempt = commands.get(cmd[0]);
                if(attempt == null) throw new IllegalArgumentException("Ilyen parancs nem létezik.");
                attempt.execute(cmd);
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
        ArrayList<Beer> local = (ArrayList<Beer>) beers.clone(); //unchecked cast shouldn't cause issues
        if(cmd.length > 1 && cmd[1] != null && comps.containsKey(cmd[1])) {
            lparams.remove(cmd[1]);
            lparams.add(0, cmd[1]);
        }

        Comparator<Beer> comp = comps.get(lparams.get(0));
        for(int i = 1; i < lparams.size(); i++){
            comp.thenComparing(comps.get(lparams.get(i)));
        }

        local.sort(comp);
        for (Beer b : local) {
            System.out.println(b);
        }
    }
    protected static void load(String[] cmd) throws IOException, ClassNotFoundException {
        if(cmd.length < 2) throw new IllegalArgumentException("Túl kevés paraméter!");
        File inFile = new File(cmd[1]);
        if (!inFile.exists()) throw new IOException("Nincs ilyen fájl!");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));
        beers = (ArrayList<Beer>) in.readObject(); //unchecked cast shouldn't cause issues
        in.close();
    }
    protected static void save(String[] cmd) throws IOException {
        if(cmd.length < 2) throw new IllegalArgumentException("Túl kevés paraméter!");
        File outFile = new File(cmd[1]);
        if(!outFile.createNewFile()) throw new IOException("Fájlkészítés hiba!");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
        out.writeObject(beers);
        out.close();
    }

    protected static void search(String[] cmd) throws IllegalArgumentException {
        if(cmd.length >= 3){
            if ("name".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getName().equals(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            } else if ("style".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getStyle().equals(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            } else if ("strength".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getStrength() == Double.parseDouble(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            }
        }
        else if (cmd.length == 2){
            for (Beer b : beers) {
                if(b.getName().equals(cmd[1]))
                    System.out.println(b);
            }
        }
        else throw new IllegalArgumentException("Túl kevés paraméter!");
    }
    protected static void find(String[] cmd) throws IllegalArgumentException {
        if(cmd.length >= 3){
            if ("name".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getName().contains(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            } else if ("style".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getStyle().contains(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            } else if ("strength".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getStrength() >= Double.parseDouble(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            }
            else if ("weaker".equals(cmd[1])) {
                for (Beer b : beers) {
                    if (b.getStrength() <= Double.parseDouble(cmd[cmd.length - 1]))
                        System.out.println(b);
                }
            }
        }
        else if (cmd.length == 2){
            for (Beer b : beers) {
                if(b.getName().equals(cmd[1]))
                    System.out.println(b);
            }
        }
        else throw new IllegalArgumentException("Túl kevés paraméter!");
    }
    protected static void delete(String[] cmd) throws IllegalArgumentException {
        if(cmd.length < 2) throw new IllegalArgumentException("Túl kevés paraméter!");
        beers.removeIf(current -> current.getName().equals(cmd[1])); //just for fun
    }
}