package org.tgi11.mastermind;

import jdk.jfr.Description;

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
			TimeUnit.SECONDS.wait(3);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Intelligenter Algorithmus zur Generierung des nächsten Zugs basierend auf den Rückmeldungen des Spiels
	public static void intelligentGuess() {
		int[] guess = new int[4]; // Der nächste intelligente Zug

		// Hier implementierst du deine Strategie, um den nächsten Zug zu generieren
		// Möglicherweise analysierst du die bisherigen Züge und deren Rückmeldungen, um eine optimale Vermutung zu machen
		// Zum Beispiel könntest du eine heuristische Methode verwenden, um die beste Vermutung zu finden

		// Nachdem der intelligente Zug generiert wurde, gib ihn an die Steuerung weiter
		strg.guess(guess);
	}
}

