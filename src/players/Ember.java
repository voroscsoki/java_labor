package players;

import java.util.Scanner;

public class Ember extends Jatekos{

    public void lep(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Aktuális tét: " + asztal.getTet() + ", mennyivel emeljek?");
        asztal.emel(scan.nextDouble());
    }
}
