package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// Collin
public class ComFrame extends JFrame {
    private int[][] board;
    private Steuerung strg;

    private String[] colorNames = {"Red", "Blue", "Yellow", "Green", "White", "Black", "Orange", "Brown"};
    private Color[] colors = {Color.GRAY,Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.BLACK, Color.ORANGE, new Color(117, 59, 0)};


    private JPanel boardPanel;

    public ComFrame(Steuerung strg) {
        super("Mastermind Spiel");
        this.strg = strg;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());
        boardPanel = new JPanel(new GridBagLayout());
        this.add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateBoardPanel() {
        boardPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        if (board != null) {

            int rowHeight = 50;
            int colWidth = 50;
            int displayLimit = 8;

            int startIndex = Math.max(0, board.length - displayLimit);
            int displayCount = Math.min(displayLimit, board.length);

            for (int i = 0; i < displayCount; i++) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

                for (int j = 0; j < 4; j++) {
                    JPanel colorPanel = new JPanel();
                    colorPanel.setBackground(colors[board[i][j]]);
                    colorPanel.setPreferredSize(new Dimension(colWidth, rowHeight));
                    rowPanel.add(colorPanel);
                }

                JTextField hintField = new JTextField(20);
                hintField.setEditable(false);
                hintField.setText("Richtige Position: " + board[i][4] + "\n Richtige Farbe: " + board[i][5]);
                rowPanel.add(hintField);

                gbc.gridy =i ;
                boardPanel.add(rowPanel, gbc);
            }
        }

        boardPanel.revalidate();
        repaint();
    }

    public void updateHistory() {
        board = strg.getHistory();
        updateBoardPanel();
    }

    public void update() {
        updateHistory();
    }
}