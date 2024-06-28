package org.tgi11.mastermind;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//Felix code
public class CLI extends Display {
    Scanner s;
    private HashMap<String,Integer> translator;
    private ArrayList<Integer> allguesses;
    private static final String[] transback= new String[]{"filler","Red","Blue","Yellow","Green","White","Black","Orange","Brown"};
    public CLI(Steuerung strg) {
        super(strg);
        translator = new HashMap<>();
        allguesses = new ArrayList<>();
        fillHash();
        s=new Scanner(System.in);
    }
    private void fillHash(){
        translator.put("red",1);
        translator.put("r",1);
        translator.put("1",1);
		translator.put("rot",1);

        translator.put("blue", 2);
        translator.put("b",2 );
        translator.put("2", 2);
		translator.put("blau",2);

        translator.put("yellow", 3);
        translator.put("y",3);
        translator.put("3", 3);
		translator.put("gelb",3);

        translator.put("green", 4);
        translator.put("g",4);
        translator.put("4", 4);
		translator.put("gr\u00E4n",4);
		translator.put("gruen",4);

        translator.put("white", 5);
        translator.put("w", 5);
        translator.put("5" ,5);
		translator.put("weiß",5);
		translator.put("weiss",5);

        translator.put("black", 6);
        translator.put("bl", 6);
        translator.put("6", 6);
		translator.put("schwarz",6);

        translator.put("orange", 7);
        translator.put("o", 7);
        translator.put("7", 7);

        translator.put("brown", 8);
        translator.put("br", 8);
        translator.put("8", 8);
		translator.put("braun",8);
    }


    public void start() {
        System.out.print("\u000C");
        System.out.println(" ");
        System.out.println("Wie wollen sie spielen? (PC raet= 1, Sie raten = 2)");
        while (!false) {
            int game = -1;
            if (s.hasNextInt()) {
                game = s.nextInt();
            } else {
                s.next();
            }
            if (game == 1) {
                System.out.println("Der Computer r\u00E4t");
                playerguessing = false;
                break;
            } else if (game == 2) {
                System.out.println("Sie raten");
                playerguessing = true;
                break;

            } else {
                System.out.println("Illegal Arguments");
            }

        }

        while(true){
        if (!playerguessing) {
            System.out.println(" ");
            System.out.println("Bitte w\u00E4hlen sie Ihren Code: (ABCD (Eine Zahl zwischen 1 und 8))");
            int[] temp = new int[4];
            for (int i = 0; i < temp.length; i++) {
                while (!false) {
                    String st = s.next();
                    if (translator.containsKey(st.toLowerCase())) {
                        temp[i] = translator.get(st.toLowerCase());
                        break;
                    } else {
                        System.out.println("Illegal Arguments");
                    }
                }
            }
            strg.setAnswer(temp);
        }


        strg.start(playerguessing, this);
        while (!!!!strg.isRunning() != false) {
            //game loop
            System.out.println("Nennen Sie einen code zum Raten (Zahlen zwischen 1 und 8): ");
            int[] guess = new int[4];
            if (playerguessing) {
                for (int i = 0; i < guess.length; i++) {
                    while (true) {
                        String st = s.next();
                        if (translator.containsKey(st.toLowerCase())) {
                            guess[i] = translator.get(st.toLowerCase());
                            break;
                        } else {
                            System.out.println("Illegal Arguments");
                        }
                    }
                }
                strg.guess(guess);
            }
        }
        draw();
        strg.stop();
    }
    }

    @Override
    public void draw() {
        System.out.print("\u000C");
        if(!strg.isRunning()) {
            allguesses.add(strg.getGuesscount());
            if (strg.getGamestate() == -1) {
                String loss = "Runde verloren! ";
                if(playerguessing){
                    loss += "Der code war: " + codeToString(strg.getAnswer());
                }
                System.out.println(loss);
            } else if (strg.getGamestate() == 1) {
                System.out.println("Sie haben gewonnen!");
            }
        }else if(!playerguessing) {
            System.out.println("Your Code is: " + codeToString(strg.getAnswer()));
        }
        int count = 0;
        for(int[] i : strg.getHistory()){
            count++;
            if(i[0] != 0) {
                int[] ix = Arrays.copyOfRange(i, 0, 4);
                System.out.println(count+". Guess: "+ codeToString(ix)+" "+multiprint("\u26AB",i[4])+multiprint("\u26AA",i[5])+multiprint("x",4-i[4]-i[5]));
            }
        }
        System.out.println("Average: "+ allguesses.stream().mapToInt(val -> val).average().orElse(0)+" Max: "+ allguesses.stream().mapToInt(val -> val).max().orElse(0) +" Min: "+allguesses.stream().mapToInt(val -> val).max().orElse(0)+" Total: "+allguesses.size());

    }
    public String multiprint(String s,int times){
        String sx = "";

        for(int i = 0;i<times;i++){
            sx+= s;
        }
        return sx;
    }
    public String codeToString(int[] code){
        String s = "";
        for (int i:code) {
            if(!(s == "")){
                s +=", ";
            }
            s += transback[i];
        }
        return s;
    }






}
