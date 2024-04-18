package org.tgi11.mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Steuerung {
    private int[] answer;
    private int[][] history = new int[8][6];
    private Solver solv;
    private Display d;
    private int guessesmade = 0;

    public Steuerung(Display d) {
        this.d = d;
        solv = new Solver(this);
    }
    public int getGuesscount(){
        return guessesmade;
    }
    public int[][] getHistory(){
        return history;
    }
	public int[] correctPosAndCol(int[] guess){

		return null;

	}
    public void setAnswer(int[] answer){
        if(this.answer == null){
            this.answer = answer;
        }
    }

    public void start(boolean playerguessing){
        answer = null;
        history = new int[8][6];
        guessesmade = 0;
						
        solv.start(!playerguessing);
    }
				
    public void guess(int[] guess){
		int[] historysave = Arrays.copyOf(guess,6);
		historysave[5] = countCorrectPositions(int[] guess);
		historysave[6] = countCorrectColors(int[] guess);
		history[guessesmade] = historysave;
        guessesmade++;
		if(guess == answer){
            //won
            return;
        }
        if(guessesmade == 8){
            //loss
            return;
        }
		
    }










    private int countCorrectColors(int[] guess) {
        int count = 0;
        List<Color> secretList = Arrays.asList(answer);
        for (int color : guess) {
            if (secretList.contains(color)) {
                count++;
                secretList.remove(color);
            }
        }

        return count-countCorrectPositions(guess);
    }

    private int countCorrectPositions(int[] guess) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (answer[i] == guess[i]) {
                count++;
            }
        }
        return count;
    }
}
