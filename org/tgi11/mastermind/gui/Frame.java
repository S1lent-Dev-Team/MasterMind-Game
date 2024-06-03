package org.tgi11.mastermind.gui;



import org.tgi11.mastermind.GUI;
import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private JButton[] colorButtons;
    private JTextArea historyArea;
    private JButton retryButton;
    private JLabel statusLabel;
    private GUI g;
    private Steuerung strg;

    public Frame(GUI g,Steuerung strg) {
        super("Mastermind Spiel");
        this.g = g;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Farbbuttons initialisieren
        JPanel colorPanel = new JPanel(new FlowLayout());
        String[] colors = {"Rot", "Blau", "Grün", "Gelb", "Schwarz", "Weiß"};
        colorButtons = new JButton[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton(colors[i]);
            colorPanel.add(colorButtons[i]);
        }

        // Historie-Bereich
        historyArea = new JTextArea(10, 50);
        historyArea.setEditable(false);

        // Statuslabel
        statusLabel = new JLabel("Bereit zu spielen!");

        // Retry-Button
        retryButton = new JButton("Erneut versuchen");
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        add(colorPanel, BorderLayout.NORTH);
        add(new JScrollPane(historyArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        add(retryButton, BorderLayout.EAST);

        setVisible(true);
    }

    private void restartGame(){
        strg.stop();
        while(true){
            if (!strg.isSolverRunning()){
                strg.start(true, g);
                break;
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}



