package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.Frame;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI extends Display{
	public GUI(Steuerung strg){
		super(strg);
}

	@Override
	public void start() {
		Frame frame = new Frame();
	}

	@Override
	public void draw() {

	}

}
