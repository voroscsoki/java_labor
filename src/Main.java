import tables.*;
import players.*;

public class Main {
    public static void main(String[] args){
        Asztal a = new Asztal();
        try{
            a.addJatekos(new Kezdo("Karcsi"));
            a.addJatekos(new Kezdo("János"));
            a.addJatekos(new Robot());
            a.addJatekos(new Ember());
            a.addJatekos(new Mester(3));
            a.addJatekos(new Nyuszi("Piros"));
            a.ujJatek();
            for(int i = 0; i < 10; i++) {
                a.kor();
            }

            a = new Asztal();
            a.kor();
        }
        catch (NincsJatekos e){
            System.out.println("Üres asztalon próbáltál kört indítani! (" + e.getClass() + ")");
        }
        finally {
            a = null;
            System.gc();
        }
    }
}