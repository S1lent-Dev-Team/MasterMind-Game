package org.tgi11.mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

// Jonas(Der E-chte)
public class Steuerung {
    private int[] answer;
    private boolean running = false;
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
	public int[] getLastGuess(){
		if(guessesmade = 0){
			return null;
		}
		return history[guessesmade -1];
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
		running = true;
        solv.start(!playerguessing);
    }
				
    public void guess(int[] guess){
		int[] historysave = guess.clone();
		historysave[5] = countCorrectPositions(guess);
		historysave[6] = countCorrectColors(guess);
		history[guessesmade] = historysave;
        guessesmade++;
		if(guess == answer){
            //won
            running = false;
            return;
        }
        else if(guessesmade == 8){
            //loss
            running = false;
            return;
        }
		d.draw();
		
    }

    private int countCorrectColors(int[] guess) {
        int count = 0;
        int[] answercopy = guess.clone();
        for (int guesscolor : guess) {
			for(int i = 0;i < answercopy.length;i++){		
            	if (guesscolor == answercopy[i]) {
					count++;
					answercopy[i] = -1;
					break;
				}
			}
        }

        return count-countCorrectPositions(guess);
    }

    private int countCorrectPositions(int[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (answer[i] == guess[i]) {
                count++;
            }
        }
        return count;
    }
	isRunning(){
		return running;
	}
}
