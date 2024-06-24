package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;


public class ComFrame extends JFrame {

    private int[][] board;
    private Steuerung strg;

    public ComFrame(Steuerung strg) {
        super("Mastermind Spiel");
        this.strg = strg;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void update(Graphics g) {
        board = strg.getHistory();
        System.out.println("UwU");
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        int x = 10;
        int y = 20;
        for (int[] ints : board) {
            StringBuilder line = new StringBuilder();
            for (int anInt : ints) {
                line.append(anInt).append(" ");
            }
            g.drawString(line.toString(), x, y);
            y += 15;
        }
        paint(g);
    }

    public void update(){
        System.out.println("UpUdate UwU");
        board = strg.getHistory();
        repaint();
    }
}



