package org.tgi11.mastermind;
import java.util.Scanner;

//Felix
public class CLI extends Display {


    public CLI(Steuerung strg) {
        super(strg);
    }


    public void abfrage() {
        Scanner s = new Scanner(System.in);
        System.out.println("");
        System.out.println("Wie wollen sie spielen? (PC = 1, Sie = 2"); //Entscheidung zwischen Solver und selber raten
        if (s.hasNextInt()) {
            int game = s.nextInt();
            switch (game) {
                case 1:
                    System.out.println("Der Computer rät");
                    break;
                case 2:
                    System.out.println("Sie raten");
                    break;

                default:
                    System.out.println("Ungültige Eingabe");
            }
            s.close();
        }
    }
}
