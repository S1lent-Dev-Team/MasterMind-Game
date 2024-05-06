package org.tgi11.mastermind;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Collin
public class Solver {
	private static Steuerung strg;
	private static Random rand = new Random();
	private boolean intelGuess = false;

	public Solver(Steuerung strg) {
		this.strg = strg;
	}

	public void start(boolean comguessing) {
		if (comguessing) {
			if(intelGuess){
			strg.guess(new int[]{1,1,2,2});
			}else {
				randomGuess();
			}
			while (strg.isRunning()) {
				systemWait();
				if (intelGuess) {
					intelligentGuess();
				} else{
					randomGuess();
				}
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
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Intelligenter Mastermind Algorithmus zur Generierung des nächsten Zugs basierend auf den Rückmeldungen des Spiels
	public static void intelligentGuess() {
		int[] guess = new int[4];
		int[][] history = strg.getHistory();
		int[] latestGuess = strg.getLatestGuess();
		int correctPositionAndColor = latestGuess[4]; //x
		int correctColor = latestGuess[5]; //y
		//p = 4
		// Heuristische Methode zur Generierung des nächsten Zugs
		// Beispiel: Wähle die häufigsten Farben aus, die nicht bereits in der letzten Vermutung enthalten waren


		/*
		s = 4096
		1er guess = 1122

		Falsche Farben -> Nichtmehr benutzen
		Wenn nicht position: S - guess code z.B. rot,gelb,grün,blau -> Stimmt nur Farbe: Dump codes mit R,G,GR,B an falscher Stelle -> Speicher Farbe dann dump
		Wenn x >= 1 -> dump nicht
		Wenn y = 0 ->dump

		=> Mach guess basierend auf geg. Pos + Farben
		*/
		strg.guess(guess);
	}




}

