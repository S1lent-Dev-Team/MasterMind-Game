package org.tgi11.mastermind;


public abstract class Display{
	private Steuerung strg;
	public Display(Steuerung strg){
		this.strg = strg;
	}
	public void start(){

		strg.start(isPlayerGuessing());
	}
	protected abstract boolean isPlayerGuessing();
}