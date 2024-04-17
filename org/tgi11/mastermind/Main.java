package org.tgi11.mastermind;
import java.util.Scanner;

public class Main {
private static Display d;
    public static void main(String[] args) {
        Steuerung strg = new Steuerung(d);
        System.out.println("With GUI?");
        System.out.println("Yes(1) No(0)");
        Scanner s = new Scanner(System.in);
        boolean shouldgui = false;
        if (s.hasNextInt()) {
            if (s.nextInt() == 1) {
                shouldgui = true;
            }
        }
        if (shouldgui) {
            d = new GUI(strg);
        } else {
            d = new CLI(strg);
        }
        d.start();
    }
}
