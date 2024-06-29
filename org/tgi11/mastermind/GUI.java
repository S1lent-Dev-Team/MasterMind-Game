package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.*;


import javax.swing.*;

// Collin
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
                    comFrame = new ComFrame(strg);
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
            playFrame = new PlayFrame();
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
