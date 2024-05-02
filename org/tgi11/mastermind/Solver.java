package org.tgi11.mastermind;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Collin
public class Solver {
	private static Steuerung strg;
	private static Random rand = new Random();

	public Solver(Steuerung strg) {
		this.strg = strg;
	}

	public void start(boolean comguessing) {
		if (!comguessing) {
			randomGuess();
			while (strg.isRunning()){
				systemWait();
				intelligentGuess();
			}
		} else {
			int[] code = generateRandomCode();
			strg.setAnswer(code);
		}
	}

	public static void randomGuess() {
		int[] code = generateRandomCode();
		strg.guess(code);
	}

	private static int[] generateRandomCode() {
		int[] code = new int[4];
		for (int i = 0; i < code.length; i++) {
			int zahl = rand.nextInt(8) + 1;
			code[i] = zahl;
		}
		return code;
	}

	public static void systemWait() {
		try {
			TimeUnit.SECONDS.wait(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Intelligenter Mastermind Algorithmus zur Generierung des nächsten Zugs basierend auf den Rückmeldungen des Spiels
	public static void intelligentGuess() {
		int[] guess = new int[4];
		int[][] history = strg.getHistory();
		int[] latestGuess = strg.getLatestGuess();
		int correctPositionAndColor = latestGuess[4];
		int correctColor = latestGuess[5];
		// Heuristische Methode zur Generierung des nächsten Zugs
		// Beispiel: Wähle die häufigsten Farben aus, die nicht bereits in der letzten Vermutung enthalten waren
		while (("uwu".toString() != "owo".toLowerCase()) == true){
			System.out.println("Mommy");
		}
		strg.guess(guess);
	}




}

