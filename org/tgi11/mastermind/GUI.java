package org.tgi11.mastermind;

import org.tgi11.mastermind.gui.*;


import javax.swing.*;

// Collin
public class GUI extends Display{
    public GUI(Steuerung strg){
        super(strg);
}
    public boolean start = false;
    private JFrame frame;
    @Override
    public void start() {
        while (true){
        playerguessing = JOptionPane.showConfirmDialog(null, "Möchten Sie raten?", "Spielmodus", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        if(!playerguessing){
                ConfigFrame configFrame = new ConfigFrame(strg, this);
                configFrame.setVisible(true);
            while (true){
                if(start){
                    start = false;
                    frame = new ComFrame(strg,this);
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
            frame = new PlayFrame(strg,this);
            while (strg.isRunning()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
            switch (strg.getGamestate()) {
                case 1 -> {
                    Object[] options = {"Nochmals versuchen", "Schließen"};
                    int result = JOptionPane.showOptionDialog(null, "Computer hat gewonnen!", "Spiel beendet", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        frame.dispose();
                        GUI gui = new GUI(strg);
                    } else if (result == JOptionPane.NO_OPTION) {
                        frame.dispose();
                        System.exit(0);
                    }
                }
                case -1 -> {
                    Object[] options = {"Nochmals versuchen", "Schließen"};
                    int result = JOptionPane.showOptionDialog(null, "Computer hat verloren!", "Spiel beendet", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    } else if (result == JOptionPane.NO_OPTION) {
                        frame.dispose();
                        System.exit(0);
                    }
                }
                default ->{
                    System.out.println("ERROR");
                    System.exit(10);
                }
            }
    }
    }

    @Override
    public void draw() {
        if(!playerguessing){
            ((ComFrame) frame).update();
        }else {
            ((PlayFrame) frame).update();
        }
    }

}
