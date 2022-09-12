package players;
import tables.*;

public abstract class Jatekos {

    protected Asztal asztal;
    public void lep(){
        System.out.println("Kör: " + asztal.getKor() + " Tét: " + asztal.getTet());
    }
    public void setAsztal(Asztal a){
        this.asztal = a;
    }

    protected void finalize(){
        System.out.println(this.hashCode() + " " + this);
    }
}
