package org.tgi11.mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Steuerung {
				private Color[] answer;
    private Color[][] history = new Color[8][4];
				private Solver solv;
				private Display d;
				private int guessesmade = 0;

    public Steuerung(Display d) {
					this.d = d;
					solv = new Solver(this);
    }
				public getGuesscount(){
						return guessesmade;
				}
    public Color[][] getHistory(){
        return history;
    }
				public void setAnswer(Color[] answer){
						if(this.answer == null){
						this.answer = answer;
						}
				}

    public void start(boolean playerguessing){
						answer = null;
						history = new Color[8][4];
						guessmade = 0;
						
						solv.start(!playerguessing);
    }
				
    public void guess(Color[] guess){
						history[guessesmade] = guess;
						guessesmade++;
						if(guessesmade = 8){
								//loss
								return;
						}
						if(guess == answer){
								//won
								return;
						}
    }










    private int countCorrectColors(Color[] guess) {
        int count = 0;
        ArrayList<Color> secretList = (ArrayList<Color>) Arrays.asList(answer);
        for (Color color : guess) {
            if (secretList.contains(color)) {
                count++;
                secretList.remove(String.valueOf(color));
            }
        }

        return count-countCorrectPositions(guess);
    }

    private int countCorrectPositions(Color[] guess) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (answer[i] == guess[i]) {
                count++;
            }
        }
        return count;
    }
}
