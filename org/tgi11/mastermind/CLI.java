package org.tgi11.mastermind;
import java.util.Scanner;

//Felix
public class CLI extends Display {
Scanner s;

    public CLI(Steuerung strg) {
        super(strg);
        s=new Scanner(System.in);
    }


    public void start() {
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
                    System.out.println("Der Computer raet");
                    playerguessing = false;
                    break;
                } else if (game == 2) {
                    System.out.println("Sie raten");
                    playerguessing = true;
                    break;

                }else{
                        System.out.println("Ungueltige Eingabe");
                }

        }

        System.out.println(" ");
        System.out.println("Bitte waehlen sie Ihren Code: ");
        while (!playerguessing){
            if(s.hasNextInt()){
                int t;
                t = s.nextInt();
            } else {
                System.out.println("Ungueltige Eingabe.");
                s.next();
            }




            //TODO:ask for the code with numbers 1-8
            //TODO:send code to strg with strg.setAnswer(); and then break;
        }
        strg.start(playerguessing);
        if(playerguessing){
            //TODO:player guessing process. maybe change if to while if needed;
            //TODO:use strg.guess(); for a guess;
        }

    }

    @Override
    public void draw() {
        strg.getLatestGuess();
    }
}
