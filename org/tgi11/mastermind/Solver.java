package org.tgi11.mastermind;



import java.util.Random;
import java.util.stream.IntStream;

// Felix,Collin bisschen schlechter code, Jonas
public class Solver {
	private  Steuerung strg;
	private static Random rand = new Random();
	private boolean intelGuess = false;

	private boolean solverRunning = false;
	private IntStream canBeList;

	public Solver(Steuerung strg) {
		this.strg = strg;
	}

	public boolean isSolverRunning() {
		return solverRunning;
	}

	public void start(boolean comguessing) {
		solverRunning = true;
		canBeList = IntStream.rangeClosed(1111,8888);
		canBeList = filterStreamContain(canBeList,0);
		canBeList = filterStreamContain(canBeList,9);
		if (comguessing) {
			if(intelGuess){
			int x = rand.nextInt(6)+1;
			strg.guess(new int[]{x,x,x+1,x+1});
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
		solverRunning = false;
	}

	public void randomGuess() {
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

	public void systemWait() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}



	// Intelligenter Mastermind Algorithmus zur Generierung des nächsten Zugs basierend auf den Rückmeldungen des Spiels
	public void intelligentGuess() {
		int[] guess = new int[4];
		int[][] history = strg.getHistory();
		int[] latestGuess = strg.getLatestGuess();
		int cP = latestGuess[4]; //x
		int cC = latestGuess[5]; //y
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
	public IntStream filterStreamContain(IntStream intStream,int... filter){
		IntStream fStream = intStream.filter(num ->{
			boolean keep = true;
			for(int i : filter){
				if (String.valueOf(num).contains(String.valueOf(i))){
					keep = false;
				}
			}
            return keep;
        });

		return fStream;
	}
	public IntStream filterStreamPosition(IntStream intStream,int[] filter){//0 als filtereitrag bedeutet Slot überspringen
		IntStream fStream = intStream.filter(num ->{
			for(int i = 3; i >= 0; i++) {
				if(filter[i] == num % 10 && filter[i] <= 0){
						return false;
				}
				num = num / 10;
			}
            return true;
        });

		return fStream;
	}


	public void miniMax(){
		int bestguess = -1;

		for(int i : canBeList.toArray()){
			for (int k : canBeList.toArray()){


			}
		}

	}









}

