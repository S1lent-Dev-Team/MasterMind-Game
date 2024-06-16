package org.tgi11.mastermind.gui;
import org.tgi11.mastermind.GUI;
import org.tgi11.mastermind.Solver;
import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Frame extends JFrame {
    private JButton[] colorButtons;
    private JTextArea historyArea;
    private JButton retryButton;
    private JLabel statusLabel;
    private GUI g;
    private Steuerung strg;
    private boolean playerGuessing;

    public Frame(GUI g, Steuerung strg) {
        super("Mastermind Spiel");
        this.g = g;
        this.strg = strg;
        this.playerGuessing = true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel colorPanel = new JPanel(new FlowLayout());
        String[] colors = {"Red", "Blue", "Yellow", "Green", "White", "Black", "Orange", "Brown"};
        colorButtons = new JButton[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton(colors[i]);
            colorButtons[i].setActionCommand(colors[i]);
            colorButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleColorButtonClick(e.getActionCommand());
                }
            });
            colorPanel.add(colorButtons[i]);
        }

        historyArea = new JTextArea(10, 50);
        historyArea.setEditable(false);

        statusLabel = new JLabel("Bereit zu spielen!");

        retryButton = new JButton("Erneut versuchen");
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(statusLabel, BorderLayout.CENTER);
        controlPanel.add(retryButton, BorderLayout.EAST);

        add(colorPanel, BorderLayout.NORTH);
        add(new JScrollPane(historyArea), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleColorButtonClick(String color) {
        if (strg.isRunning() && playerGuessing) {
            int[] guess = convertColorToCode(color);
            strg.guess(guess);
            updateHistory();
            updateStatusLabel();
        }
    }

    private int[] convertColorToCode(String color) {
        return switch (color) {
            case "Red" -> new int[]{0};
            case "Blue" -> new int[]{1};
            case "Yellow" -> new int[]{2};
            case "Green" -> new int[]{3};
            case "White" -> new int[]{4};
            case "Black" -> new int[]{5};
            case "Orange" -> new int[]{6};
            case "Brown" -> new int[]{7};
            default -> new int[]{-1};
        };
    }

    private void restartGame() {
        strg.stop();
        playerGuessing = JOptionPane.showConfirmDialog(this, "Möchten Sie raten?", "Spielmodus", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        strg.start(playerGuessing, g);
        updateHistory();
        updateStatusLabel();
        if (!playerGuessing) {
            runSolver();
        }
    }

    private void runSolver() {
        new Thread(() -> {
            while (strg.isRunning()) {
                try {
                    Thread.sleep(1000);
                    if (!playerGuessing) {
                        Solver solver = new Solver(strg);
                        solver.start(false);
                        updateHistory();
                        updateStatusLabel();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void updateHistory() {
        int[][] history = strg.getHistory();
        historyArea.setText("");
        for (int[] guess : history) {
            if (guess != null) {
                historyArea.append(Arrays.toString(guess) + "\n");
            }
        }
    }

    public void updateStatusLabel() {
        if (strg.getGamestate() == 1) {
            statusLabel.setText("Gewonnen!");
        } else if (strg.getGamestate() == -1) {
            statusLabel.setText("Verloren!");
        } else {
            statusLabel.setText("Bereit zu spielen!");
        }
    }
}