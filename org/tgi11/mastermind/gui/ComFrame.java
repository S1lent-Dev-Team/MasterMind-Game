package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ComFrame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int ROWS = 10;
    private static final int COLUMNS = 4;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.BLACK, Color.ORANGE, Color.DARK_GRAY};
    private Color[][] board;
    private Color[] solution;
    private Steuerung strg;

    public ComFrame(Steuerung strg) {
        super("Mastermind Spiel");
        this.strg = strg;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());

        board = new Color[ROWS][COLUMNS];
        generateSolution();

        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - boardPanel.getX();
                int y = e.getY() - boardPanel.getY();
                int row = y / (HEIGHT / ROWS);
                int col = x / (WIDTH / COLUMNS);
                if (row < ROWS && col < COLUMNS) {
                    board[row][col] = COLORS[new Random().nextInt(COLORS.length)];
                    boardPanel.repaint();
                }
            }
        });

        setVisible(true);
    }

    private void generateSolution() {
        solution = new Color[COLUMNS];
        Random rand = new Random();
        for (int i = 0; i < COLUMNS; i++) {
            solution[i] = COLORS[rand.nextInt(COLORS.length)];
        }
    }

    private class BoardPanel extends JPanel {
        public BoardPanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    if (board[row][col] != null) {
                        g.setColor(board[row][col]);
                    } else {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    int x = col * (WIDTH / COLUMNS) + (WIDTH / COLUMNS - CIRCLE_DIAMETER) / 2;
                    int y = row * (HEIGHT / ROWS) + (HEIGHT / ROWS - CIRCLE_DIAMETER) / 2;
                    g.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
                }
            }
        }
    }
}



