package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.*;

import java.util.Scanner;

import org.tgi11.mastermind.gui.Frame;

import javax.swing.*;

public class GUI extends Display{
    public GUI(Steuerung strg){
        super(strg);
}
public boolean start = false;
    private ComFrame comFrame;
    private PlayFrame playFrame;
    @Override
    public void start() {
        playerguessing = JOptionPane.showConfirmDialog(null, "Möchten Sie raten?", "Spielmodus", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if(!playerguessing){
                ConfigFrame frame = new ConfigFrame(strg, this);
                frame.setVisible(true);
            while (true){
                if(start){
                    ComFrame comFrame = new ComFrame(strg);
                    this.comFrame = comFrame;
                strg.start(false, this);

                break;}
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }else{
            strg.start(true, this);
            PlayFrame playFrame = new PlayFrame();
            this.playFrame = playFrame;
        }

    }

    @Override
    public void draw() {
        if(!playerguessing){
            comFrame.update();
        }else {

        }
    }

}
