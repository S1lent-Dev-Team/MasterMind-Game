package org.tgi11.mastermind;



import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

// Felix,Collin bisschen schlechter code, Jonas
public class Solver {
	private  Steuerung strg;
	private static Random rand = new Random();
	private boolean intelGuess = false;

	private boolean solverRunning = false;
	private IntStream canBeStream;

	public Solver(Steuerung strg) {
		this.strg = strg;
	}

	public boolean isSolverRunning() {
		return solverRunning;
	}

	public void start(boolean comguessing) {
		solverRunning = true;
		canBeStream = IntStream.rangeClosed(1111,8888);
		canBeStream = filterStreamContain(canBeStream,0);
		canBeStream = filterStreamContain(canBeStream,9);
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
		int[] latestGuess = Arrays.stream(strg.getLatestGuess()).limit(4).toArray();
		int cP = strg.getLatestGuess()[4]; //x
		int cC = strg.getLatestGuess()[5]; //y
		//p = 4


		//Delete Welche nich sein können (Impossible cases){
		switch (cC) {
			case 0 -> {
				for (int i = 0; i < 4; i++) {
					canBeStream = filterStreamContain(canBeStream, latestGuess[i]);
				}
			}
			case 1 -> {
				for (int i = 0; i < 3; i++) {
					for (int k = i; k < 4; k++) {
						canBeStream = filterStreamContain(canBeStream, latestGuess[i], latestGuess[k]);
					}
				}
			}
			case 2-> {
				for (int i = 0; i < 2; i++) {
					for (int k = i; k < 3; k++) {
						for (int j = k; j < 4; j++) {
							canBeStream = filterStreamContain(canBeStream, latestGuess[i], latestGuess[k], latestGuess[j]);
						}
					}
				}
			}
			case 3,4 ->{
				canBeStream = filterStreamContain(canBeStream, latestGuess);
			}
        }

		// Heuristische Methode zur Generierung des nächsten Zugs
		// Beispiel: Wähle die häufigsten Farben aus, die nicht bereits in der letzten Vermutung enthalten waren


// Lösch code, ohne paasende farbe.
// Behalt alle codes die 1 oder mehr cC oder cP haben.
// lösch alle codes, die weniger cP oder cC haben, als der latestGuess; latestGuess cP 3, cC 0 -> delete alle codes die weniger als cP = 3 haben.







		/*
		s = 4096
		1er guess = 1122

		Falsche Farben -> Nichtmehr benutzen
		Wenn nicht position: S - guess code z.B. rot,gelb,grün,blau -> Stimmt nur Farbe: Dump codes mit R,G,GR,B an falscher Stelle -> Speicher Farbe dann dump
		Wenn x >= 1 -> dump nicht
		Wenn y = 0 ->dump

		=> Mach guess basierend auf geg. Pos + Farben
		*/
		//}
		strg.guess(miniMax());
	}
	public IntStream filterStreamContain(IntStream intStream,int... filter){
		IntStream fStream = intStream.filter(num ->{
			int keep = 0;
			for(int i : filter){
				if (String.valueOf(num).contains(String.valueOf(i))){
					keep++;
				}
			}
            return keep != filter.length;
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


	public int[] miniMax(){
		int[] canBeList = canBeStream.toArray();
		canBeStream = Arrays.stream(canBeList);
		int bestguess = 1111;
		int lowestnum = canBeList.length;

		for(int i : canBeList){
			int worstcase = -1;
			for(int k=0; k < 14;k++){//weiß nicht ob 14 wxxx 1111
				IntStream tempstream = Arrays.stream(canBeList);
				int cC; //correctColor
				int cP; //correctPosition
				//calc
				switch(k){
					case 0 -> {
						cC = 0;
						cP = 0;
					}
					case 1 -> {
						cC = 0;
						cP = 1;
					}

					case 2 -> {
						cC = 0;
						cP = 2;
					}

					case 3 -> {
						cC = 0;
						cP = 3;
					}

					case 4 -> {
						cC = 0;
						cP = 4;
					}

					case 5 -> {
						cC = 1;
						cP = 0;
					}

					case 6 -> {
						cC = 2;
						cP = 0;
					}

					case 7 -> {
						cC = 3;
						cP = 0;
					}

					case 8 -> {
						cC = 4;
						cP = 0;
					}

					case 9 -> {
						cC= 3;
						cP = 1;
					}

					case 10 -> {
						cC = 2;
						cP = 1;
					}

					case 11 -> {
						cC = 1;
						cP = 1;
					}

					case 12 -> {
						cC = 2;
						cP = 2;
					}

					case 13 -> {
						cC = 1;
						cP = 2;
					}
				}

// cP = 0 und cC = 0 -> case 0
// cP = 1 bis 3 cC = 0 -> cases 1 bis 3 -> case 1 ->{cC = 0; cP = 1;}
// cP = 0 cC = 1 bis 4 -> cases 5 bis 8
// cP = 3, cC = 1 -> case 9
// cP = 2, cC = 2 -> case 10
// cP = 1, cC = 3 -> case 11



				int count = (int) tempstream.count();
				if(count >=worstcase){
					worstcase = count;
				}
			}
			if(worstcase < lowestnum){
				bestguess = i;
				lowestnum = worstcase;
			}
		}
		return intToArray(bestguess);

	}
	public int[] intToArray(int x){
				int j = 0;
				int size = Integer.toString(x).length();
				int[] bguess = new int[size];
				while(x!=0){
					bguess[size-j-1] = x%10;
					x=x/10;
					j++;
				}
		return bguess;
	}









}

