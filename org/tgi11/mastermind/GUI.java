package org.tgi11.mastermind;


import org.tgi11.mastermind.gui.Frame;


public class GUI extends Display{
	public GUI(Steuerung strg){
		super(strg);
}

	@Override
	public void start() {
		Frame f = new Frame(this,strg);
	}

	@Override
	public void draw() {

	}

}
