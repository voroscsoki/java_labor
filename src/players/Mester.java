package players;

public class Mester extends Jatekos {
    private final int mesterfokozat;

    public Mester(int mesterfokozat) {
        this.mesterfokozat = mesterfokozat;
    }

    public String toString(){
        return "Mester #" + mesterfokozat;
    }
    public void lep(){
        System.out.println(this + ": " + asztal.getKor()); //toStringet is hívja
        asztal.emel(asztal.getTet()*0.01*mesterfokozat);
    }
}
