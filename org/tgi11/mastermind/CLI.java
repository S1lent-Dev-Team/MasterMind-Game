package org.tgi11.mastermind;
import java.util.Scanner;

//Felix
public class CLI extends Display {


    public CLI(Steuerung strg) {
        super(strg);
    }


    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Wie wollen sie spielen? (PC raet= 1, Sie raten = 2)"); //Entscheidung zwischen Solver und selber raten
        while (true) {
            int game = -1;
            if(s.hasNextInt()) {
                game = s.nextInt();
            }else{
                s.next();
            }
                if(game == 1) {
                    System.out.println("Der Computer rät");
                    playerguessing = false;
                    break;
                } else if (game == 2) {
                    System.out.println("Sie raten");
                    playerguessing = true;
                    break;

                }else{
                        System.out.println("Ungültige Eingabe");
                }

        }


        super.start();
    }
}
