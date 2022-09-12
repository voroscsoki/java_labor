package players;

public class Kezdo extends Jatekos{
    private final String nev;

    public Kezdo(String nev) {
        this.nev = nev;
    }
    public String toString(){
        return nev;
    }
    public void lep(){
        int kor = asztal.getKor();
        System.out.println(this + ": " + kor);
        if(kor % 2 == 0) {asztal.emel(1);}
    }
}
