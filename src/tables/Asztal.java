package tables;

import java.util.ArrayList;
import java.util.Random;
import players.*;

public class Asztal {
    private final ArrayList<Jatekos> jatekosok = new ArrayList<>();
    private double tet;
    private int kor;
    private double goal;
    private final Random r = new Random();

    public void ujJatek() {
        tet = 0;
        kor = 0;
        goal = r.nextDouble(0, 100);
    }
    public void addJatekos(Jatekos j) {
        if(jatekosok.size() == 10) {
            System.out.println("Az asztal megtelt!");
            return;
        }
        jatekosok.add(j); //vagy új array, másolás, referencia átírás
        j.setAsztal(this);
    }

    public int getKor() { return this.kor; }
    public double getTet() { return this.tet; }
    public void emel(double d) { tet += d; }

    public void kor() throws NincsJatekos {
        if (jatekosok.size() == 0) throw new NincsJatekos();
        kor++;
        if (tet >= goal){
            System.out.println("Vége a játéknak.");
            return;
        }
        int i = 0;
        for (Jatekos j : jatekosok){
            i++;
            j.lep();
            if (tet >= goal){
                System.out.println((tet < (goal*1.1) ? i + ". játékos nyert." : "Senki sem nyert"));
                return;
            }
        }
        System.out.println("A tét jelenleg: " + tet + "\n----------");

    }


}
