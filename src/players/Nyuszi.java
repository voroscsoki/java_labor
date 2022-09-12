package players;

public class Nyuszi extends Jatekos{
    String szin;

    public Nyuszi(String szin) {
        this.szin = szin;
    }

    public String toString() { return szin; }
    public void lep() {
        System.out.println(szin + " nyuszi: " + asztal.getKor());
        if(asztal.getTet() < 50) asztal.emel(5);
        else System.out.println("Húha! A tét: " + asztal.getTet());
    }
}
