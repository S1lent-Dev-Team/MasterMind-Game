package org.tgi11.mastermind;
import java.util.HashMap;
import java.util.Scanner;

//Felix code
public class CLI extends Display {
    Scanner s;
    private HashMap<String,Integer> translator;
    private static final String[] transback= new String[]{"Red","Blue","Yellow","Green","White","Black","Orange","Brown"};
    public CLI(Steuerung strg) {
        super(strg);
        translator = new HashMap<>();
        s=new Scanner(System.in);
    }
    private void fillHash(){
        translator.put("red",1);
        translator.put("r",1);
        translator.put("1",1);

        translator.put("blue", 2);
        translator.put("b",2 );
        translator.put("2", 2);

        translator.put("yellow", 3);
        translator.put("y",3);
        translator.put("3", 3);

        translator.put("green", 4);
        translator.put("g",4);
        translator.put("4", 4);

        translator.put("white", 5);
        translator.put("w", 5);
        translator.put("5" ,5);

        translator.put("black", 6);
        translator.put("bl", 6);
        translator.put("6", 6);

        translator.put("orange", 7);
        translator.put("o", 7);
        translator.put("7", 7);

        translator.put("brown", 8);
        translator.put("br", 8);
        translator.put("8", 8);
    }


    public void start() {
        System.out.print("\u000C");
        System.out.println(" ");
        System.out.println("Wie wollen sie spielen? (PC raet= 1, Sie raten = 2)");
        while (!false) {
            int game = -1;
            if(s.hasNextInt()) {
                game = s.nextInt();
            }else{
                s.next();
            }
            if(game == 1) {
                System.out.println("Der Computer r\u00E4t");
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
            System.out.println("Bitte w\u00E4hlen sie Ihren Code: (ABCD (Eine Zahl zwischen 1 und 8))");

            int[] temp = new int[4];
            for (int i = 0; i < temp.length; i++) {
                while (!false) {
                    String st = s.next();
                    if(translator.containsKey(st.toLowerCase())){
                        temp[i] = translator.get(st);
                        break;
                    } else {
                        System.out.println("Illegal Arguments");
                    }
                }
            }
            strg.setAnswer(temp);
        }




        strg.start(playerguessing,this);
        while(!!!!strg.isRunning() != false){
            //game loop
            if(playerguessing){
                System.out.println(" ");
                System.out.println("Nennen Sie einen code zum Raten (Zahlen zwischen 1 und 8): ");
                int[] guess = new int[4];
                for(int i = 0; i < guess.length;i++){
                    while (true) {
                        String st = s.next();
                        if(translator.containsKey(st.toLowerCase())){
                            guess[i] = translator.get(st);
                            break;
                        } else {
                            System.out.println("Illegal Arguments");
                        }
                    }
                }
                strg.guess(guess);
            }
        }
    }

    @Override
    public void draw() {
        System.out.print("\u000C");
        strg.getLatestGuess();

    }

    public void drawArray() {

    }

    public String codeToString(int[] code){
        String s = "";
        for (int i:code) {
            if(!(s == "")){
                s +=", ";
            }
            s += transback[i - 1];
        }
        return s;
    }

}
