package org.tgi11.mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Steuerung {

    public final Color[] answer;

    private static Color[] secretCode = new Color[8];

    public Steuerung(Color[] answer) {
        this.answer = answer;
    }
    public Color[][] getHistory(){
        return null;
    }
    public Color[][] getReturnHistory(){
        return null;
    }
    public void guess(Color[] guess){

    }
    private static int countCorrectColors(Color[] secretCode, Color[] guess) {
        int count = 0;
        List secretList = new ArrayList<>(Arrays.asList(secretCode));
        for (Color color : guess) {
            if (secretList.contains(color)) {
                count++;
                secretList.remove(String.valueOf(color));
            }
        }
        return count;
    }

    private static int countCorrectPositions(Color[] guess) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (secretCode[i] == guess[i]) {
                count++;
            }
        }
        return count;
    }
}
