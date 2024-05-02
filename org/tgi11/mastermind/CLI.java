package org.tgi11.mastermind;
import java.util.Scanner;

//Felix
public class CLI extends Display {
Scanner s;
    private int[] temp = new int[4];

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

        System.out.println(" ");
        System.out.println("Bitte waehlen sie Ihren Code: (ABCD (Eine Zahl zwischen 1 und 8))");
    if(!playerguessing) {
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

                //TODO:ask for the code with numbers 1-8
                //TODO:send code to strg with strg.setAnswer(); and then break;
            }
        }
        strg.setAnswer(temp);
    }




    strg.start(playerguessing);
    while(strg.isRunning()){
		//gameloop
        if(playerguessing){


            //TODO:player guessing process. maybe change if to while if needed;
            //TODO:use strg.guess(); for a guess;
        }
	}

    }

    @Override
    public void draw() {
        strg.getLatestGuess();
    }
    public void drawArray(){

    }
	
}
