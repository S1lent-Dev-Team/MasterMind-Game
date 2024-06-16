package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.Frame;

import java.util.Scanner;

public class GUI extends Display{
    private Frame frame;
    public GUI(Steuerung strg){
        super(strg);
}

    @Override
    public void start() {
            Frame f = new Frame(this, strg);
    }

    @Override
    public void draw() {
        frame.updateHistory();
        frame.updateStatusLabel();
    }

}
