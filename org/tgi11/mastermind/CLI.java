package org.tgi11.mastermind;
import java.util.Scanner;

//Felix code
public class CLI extends Display {
    Scanner s;
    private int[] temp = new int[4];
    private int[] guess = new int[4];

    public CLI(Steuerung strg) {
        super(strg);
        s=new Scanner(System.in);
    }


    public void start() {
        System.out.println(" ");
        System.out.println("Wie wollen sie spielen? (PC raet= 1, Sie raten = 2)");
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
                System.out.println("Illegal Arguments");
            }

        }


        if(!playerguessing) {
            System.out.println(" ");
            System.out.println("Bitte waehlen sie Ihren Code: (ABCD (Eine Zahl zwischen 1 und 8))");
            for (int i = 0; i < temp.length; i++) {
                while (!playerguessing) {
                    if (s.hasNextInt()) {
                        int t;
                        t = s.nextInt();
                        if (t >= 1 && t <= 8) {
                            temp[i] = t;
                        }
                    } else {
                        System.out.println("Illegal Arguments");
                        s.next();
                    }
                }
            }
            strg.setAnswer(temp);
        }




        strg.start(playerguessing);
        while(strg.isRunning()){
            //game loop
            System.out.println(" ");
            System.out.println("Nennen Sie einen code zum Raten (Zahlen zwischen 1 und 8): ");
            if(playerguessing){
                for(int i = 0; i < guess.length;i++){
                    if(s.hasNextInt()) {
                        int g = s.nextInt();
                        if(g >= 1 && g <= 8) {
                            guess[i] = g;
                        }
                    } else {
                        System.out.println("Illegal Arguments");
                        s.next();
                    }
                }
            }
            strg.guess(guess);
        }
    }

    @Override
    public void draw() {
        System.out.print("\u000C");
        strg.getLatestGuess();

    }

    public void drawArray() {

    }

}
