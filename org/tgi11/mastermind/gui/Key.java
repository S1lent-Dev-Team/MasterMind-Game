package org.tgi11.mastermind.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Key extends KeyAdapter
{
    Frame frame;
    private Dimension frameSize;
    private  int frameState;
    public boolean isFullscreen() {
        return fullscreen;
    }

    private boolean fullscreen= false;
    public Key(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F11){
            if(fullscreen){
                fullscreen = false;
                frame.dispose();
                frame.setExtendedState(frameState);
                frame.setSize(frameSize);
                frame.setUndecorated(false);
                frame.setResizable(true);
                frame.setVisible(true);
            }else {
                fullscreen = true;
                frameSize = frame.getSize();
                frameState = frame.getExtendedState();
                frame.dispose();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setUndecorated(true);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        }
    }
}
