package org.tgi11.mastermind;

public class Main {
private Display d;
    public static void main(String[] args){
		Steuerung steuerung = new Steuerung();
		System.out.println("With GUI?");
		System.out.println("Yes(1) No(0)");
		Scanner s = new Scanner(System.in);
		boolean shouldgui = false;
		if(s.hasNextInt()){
			if(s.getNextInt()== "1"){
				shouldgui = true;
			}
		}
		if(shouldgui){
			d = new GUI(steuerung);
    	}else{
			d = new CLI(steuerung);
		}
}
