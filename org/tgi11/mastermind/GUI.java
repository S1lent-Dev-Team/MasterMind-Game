package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.*;

import java.util.Scanner;

import org.tgi11.mastermind.gui.Frame;

import javax.swing.*;

public class GUI extends Display{
    public GUI(Steuerung strg){
        super(strg);
}

    @Override
    public void start() {
        playerguessing = JOptionPane.showConfirmDialog(null, "Möchten Sie raten?", "Spielmodus", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if(!playerguessing){
            SwingUtilities.invokeLater(() -> {
                ConfigFrame frame = new ConfigFrame(strg);
                frame.setVisible(true);
            });
                while (true){
                    strg.start(false, this);
                    if (strg.isRunning()){
                        SwingUtilities.invokeLater(() -> {
                            ComFrame frame = new ComFrame(strg);
                            frame.setVisible(true);
                        });
                        break;
                    }
                }
        }else{
            strg.start(true, this);
            PlayFrame playFrame = new PlayFrame();
        }

    }

    @Override
    public void draw() {
        if(!playerguessing){

        }else {

        }
    }

}
