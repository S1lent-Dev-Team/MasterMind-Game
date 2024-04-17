package org.tgi11.mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Steuerung {
    public Color[] answer;
    public Color[][] history = new Color[8][4];
	private Solver solv;

    public Steuerung() {
		solv = new Solver();
    }
    public Color[][] getHistory(){
        return null;
    }
	public void setAnswer(Color[] answer){
		if(this.answer == null){
		this.answer = answer
		}
	}

    public void start(boolean playerguessing){
		solv.start(!playerguessing);
    }
	public void newGame(){
}
    public void guess(Color[] guess){

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

        return count;
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
