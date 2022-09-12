package players;
public class Robot extends Jatekos {
    private static int kovSzam = 1;
    private final int azonosito = kovSzam++;

    public String toString(){
        return "Robot" + azonosito;
    }
    public void lep(){
        int kor = asztal.getKor();
        System.out.println(this + ": " + kor);
    }
}
