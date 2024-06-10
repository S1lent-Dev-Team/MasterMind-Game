package org.tgi11.mastermind;



import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

// Felix,Collin bisschen schlechter code, Jonas
public class Solver {
	private  Steuerung strg;
	private static Random rand = new Random();
	private boolean intelGuess = true;

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
		System.out.println(intToArray(5382)[0]+","+intToArray(5382)[1]+","+intToArray(5382)[2]+","+intToArray(5382)[3]);
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
		canBeStream = deleteThisShit(canBeStream,latestGuess,cC,cP);
		strg.guess(miniMax());
	}
	public IntStream filterStreamContain(IntStream intStream,int... filter){
		IntStream fStream = intStream.filter(num ->{
			int keep = 0;
			for(int i : filter){
				if (!String.valueOf(num).contains(String.valueOf(i))){
					keep++;
				}
			}
            return keep >= filter.length;
        });

		return fStream;
	}
	public IntStream filterStreamPosition(IntStream intStream,int[] filter){//0 als filtereitrag bedeutet Slot überspringen
		IntStream fStream = intStream.filter(numIn ->{
			int[] numarr = intToArray(numIn);
for(int i = 0; i< 4;i++){
if(num[i] == filter[i]){
return false;
}
}
return true;
        });

		return fStream;
	}


	public int[] miniMax(){
		int[] canBeList = canBeStream.toArray();
		canBeStream = Arrays.stream(canBeList);
		int bestguess = 1111;
		int lowestnum = 9999999;

		for(int i : canBeList){
			int worstcase = -1;
			for(int k=0; k < 14;k++){//weiß nicht ob 14 wxxx 1111
				IntStream tempstream = Arrays.stream(canBeList);
				int cC = -1; //correctColor
				int cP = -1; //correctPosition
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
			tempstream = deleteThisShit(tempstream, intToArray(i),cC,cP);


				int count = (int) tempstream.count();
				if(count >=worstcase){
					worstcase = count;
				}
				add+= count;
			}
			if(worstcase < lowestnum){
				bestguess = i;
				lowestnum = worstcase;
			}
		}
		System.out.println(lowestnum);
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

	public IntStream deleteThisShit(IntStream streamIn, int[] guess, int cC, int cP ){
		IntStream stream = streamIn;

if(cP ==0){
			switch (cC) {
				case 0 -> {
					for (int i = 0; i < 4; i++) {
						stream = filterStreamContain(stream, guess[i]);
					}
				}
				case 1 -> {
					for (int i = 0; i < 3; i++){
						for (int k = i+1; k < 4; k++) {
							stream = filterStreamContain(stream, guess[i], guess[k]);
						}
					}
				}
				case 2-> {
					for (int i = 0; i < 2; i++) {
						for (int k = i+1; k < 3; k++) {
							for (int j = k+1; j < 4; j++) {
								stream = filterStreamContain(stream, guess[i], guess[k], guess[j]);
							}
						}
					}
				}
			}
		}
// Heuristische Methode zur Generierung des nächsten Zugs
// Beispiel: Wähle die häufigsten Farben aus, die nicht bereits in der letzten Vermutung enthalten waren


// Lösch code, ohne paasende farbe.
// Behalt alle codes die 1 oder mehr cC oder cP haben.
// lösch alle codes, die weniger cP oder cC haben, als der latestGuess; latestGuess cP 3, cC 0 -> delete alle codes die weniger als cP = 3 haben.

//Case 1 bis 3 lösch alle codes, welche cP < 1,2,3 haben, behalt alle restlichen codes. Sobald ein neuer guess mit den restlichen codes gemacht wurde, wiederhol das ganze.
//ignorier cC
//AMERICA YA :D ~Felix

		switch(cP) {
			case 0 -> {
				for(int i = 0; i < 4; i++) {
					int[] temp = new int[4];
					temp[i] = guess[i];
					stream = filterStreamPosition(stream, temp);
				}
			}

			case 1 -> {
				for (int i = 0; i < 3; i++) {
					for (int k = i + 1; k < 4; k++) {
						int[] temp = new int[4];
						temp[i] = guess[i];
						temp[k] = guess[k];
						stream = filterStreamPosition(stream, temp);

					}
				}
			}

			case 2 -> {
				for (int i = 0; i < 2; i++) {
					for (int k = i+1; k < 3; k++) {
						for (int j = k+1; j < 4; j++) {
							int[] temp = new int[4];
							temp[i] = guess[i];
							temp[k] = guess[k];
							temp[j] = guess[j];
							stream = filterStreamPosition(stream, temp);
						}
					}
				}
			}
		}

		/*
		s = 4096
		1er guess = 1122

		Falsche Farben -> Nichtmehr benutzen
		Wenn nicht position: S - guess code z.B. rot,gelb,grün,blau -> Stimmt nur Farbe: Dump codes mit R,G,GR,B an falscher Stelle -> Speicher Farbe dann dump
		Wenn x >= 1 -> dump nicht
		Wenn y = 0 ->dump

		=> Mach guess basierend auf geg. Pos + Farben
		*/
		if(cP < 4){
		stream = filterStreamContain(stream,guess);
		}
		return stream;
	}
}

