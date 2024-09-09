package org.tgi11.mastermind;


//Jonas
public abstract class Display{
	protected Steuerung strg;
	protected boolean playerguessing = false;
	public Display(Steuerung strg){
		this.strg = strg;
	}
	public abstract void start();
	public abstract void draw();
}