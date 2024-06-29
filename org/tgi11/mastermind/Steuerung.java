package org.tgi11.mastermind;

import java.util.Arrays;

// Jonas(Der E-chte)
public class Steuerung {
    private int[] answer;
    private boolean running = false;
    private int gamestate = 0;
    private int maxguesscount = 16;
    private int[][] history = new int[maxguesscount][6];
    private Solver solv;
    private Display d;
    private int guessesmade = 0;
    private boolean playerguessing;

    public Steuerung() {
        solv = new Solver(this);
    }
    public int getGuesscount(){
        return guessesmade;
    }
    public int[][] getHistory(){
        return history.clone();
    }
	public int[] getLatestGuess(){
		if(guessesmade == 0){
			return null;
		}
		return history[guessesmade -1];
	}
    public void setAnswer(int[] answer){
        if(this.answer == null){
            this.answer = answer;
        }
    }

    public void start(boolean playerguessing,Display d){
            this.d = d;
            this.playerguessing = playerguessing;
            history = new int[maxguesscount][6];
            gamestate = 0;
            guessesmade = 0;
            running = true;
            solv.start(!playerguessing);
    }
    public void stop(){
        running = false;
        answer = null;
    }

    public boolean isSolverRunning(){
        return solv.isSolverRunning();
    }
				
    public void guess(int[] guess){
        if(guess[0] == 0){
            return;
        }
		int[] historysave = Arrays.copyOf(guess,6);
		historysave[4] = countCorrectPositions(guess);
		historysave[5] = countCorrectColors(guess);
		history[guessesmade] = historysave;
        guessesmade++;
        d.draw();
		if(historysave[4] == 4){
            //won
            running = false;
            if(playerguessing){
                gamestate = 1;
            }else{
                gamestate = -1;
            }
            return;
        }
        else if(guessesmade == maxguesscount){
            //loss
            running = false;
            if(playerguessing){
                gamestate = -1;
            }else{
                gamestate = 1;
            }
            return;
        }

		
    }

    private int countCorrectColors(int[] guess) {
        int count = 0;
        int[] answercopy = answer.clone();
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

    public int[] getAnswer() {
        return answer;
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
	public boolean isRunning(){
		return running;
	}

    public int getGamestate() {
        return gamestate;
    }
}
