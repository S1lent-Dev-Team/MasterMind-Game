package org.tgi11.mastermind;
import java.util.Scanner;

public class Main {
private static Display d;
    public static void main(String[] args) {
        Steuerung strg = new Steuerung(d);
        System.out.println("With GUI?");
        System.out.println("Yes(1) No(0)");
        Scanner s = new Scanner(System.in);
        while (true) {
            int game = -1;
            if(s.hasNextInt()) {
                game = s.nextInt();
            }else{
                s.next();
            }
            if(game == 1) {
                System.out.println("GUI");
                d = new GUI(strg);
                break;
            } else if (game == 0) {
                System.out.println("CLI");
                d = new CLI(strg);
                break;

            }else{
                System.out.println("Ungültige Eingabe");
            }

        }
        d.start();
    }
}
