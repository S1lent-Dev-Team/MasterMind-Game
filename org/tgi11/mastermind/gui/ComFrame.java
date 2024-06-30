package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Display;
import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;


//Collin
public class ComFrame extends JFrame {
    private int[][] board;
    private Steuerung strg;

    private String[] colorNames = {"Red", "Blue", "Yellow", "Green", "White", "Black", "Orange", "Brown"};
    private Color[] colors = {Color.GRAY, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.BLACK, Color.ORANGE, new Color(117, 59, 0)};

    private JPanel boardPanel;
    private Display d;

    public ComFrame(Steuerung strg, Display display) {
        super("Mastermind Spiel");
        this.strg = strg;
        this.d = display;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridBagLayout());
        add(boardPanel, BorderLayout.CENTER);

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

            int displayCount = Math.min(displayLimit, board.length);

            for (int i = 0; i < displayCount; i++) {
                int boardIndex = i;
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

                for (int j = 0; j < 4; j++) {
                    JPanel colorPanel = new JPanel();
                    colorPanel.setBackground(colors[board[boardIndex][j]]);
                    colorPanel.setPreferredSize(new Dimension(colWidth, rowHeight));
                    rowPanel.add(colorPanel);
                }

                JPanel pegPanel = new JPanel();
                pegPanel.setLayout(new GridLayout(2,2,2,2));
                int cP = board[i][4];
                int cC = board[i][5];
                for (int j = 0; j < 4; j++) {
                    JPanel colorPanel = new JPanel();
                    Color c;
                    if(cP > 0){
                        c = Color.BLACK;
                        cP--;
                    }else if(cC > 0){
                        c = Color.WHITE;
                        cC--;
                    }else{
                        c= Color.GRAY;
                    }
                    colorPanel.setBackground(c);
                    colorPanel.setPreferredSize(new Dimension(colWidth/2-2, rowHeight/2-2));
                    pegPanel.add(colorPanel);
                }
                rowPanel.add(pegPanel);

                gbc.gridy = i+1;
                boardPanel.add(rowPanel, gbc);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void updateHistory() {
        board = strg.getHistory();
        if (strg.isRunning()){
            updateBoardPanel();
        }
    }

    public void update() {
        updateHistory();
    }
}