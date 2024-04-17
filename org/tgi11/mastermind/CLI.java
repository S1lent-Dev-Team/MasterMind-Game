package org.tgi11.mastermind;
import java.util.Scanner;

//Felix
public class CLI extends Display{
    Scanner s;
    public CLI(Steuerung strg){
		super(strg);
        s = new Scanner(System.in);
    }

    public void abfrage(){

    }
}
