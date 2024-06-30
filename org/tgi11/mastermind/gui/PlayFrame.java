package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayFrame extends JFrame {
    private int[][] board;
    private Steuerung strg;
    private ArrayList<Integer> farbcodes;
    private JPanel colorPanel;
    private JButton submitButton, deleteButton;
    private static final int MAX_COLORS = 4;
    private String[] colorNames = {"Red", "Blue", "Yellow", "Green", "White", "Black", "Orange", "Brown"};
    private Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.BLACK, Color.ORANGE, new Color(117, 59, 0)};
    private JPanel boardPanel;

    public PlayFrame(Steuerung strg) {
        super("Mastermind Spiel");
        this.strg = strg;
        farbcodes = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridBagLayout());
        add(boardPanel, BorderLayout.CENTER);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        colorPanel = new JPanel(new FlowLayout());
        add(colorPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        for (int i = 0; i < colorNames.length; i++) {
            JButton colorButton = new JButton(colorNames[i]);
            colorButton.setBackground(colors[i]);
            colorButton.addActionListener(new AddColorAction(colors[i], i + 1));
            buttonPanel.add(colorButton);
        }
        add(buttonPanel, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel(new FlowLayout());
        deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(new DeleteColorAction());
        controlPanel.add(deleteButton);

        submitButton = new JButton("Bestätigen");
        submitButton.addActionListener(new SubmitColorsAction());
        controlPanel.add(submitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private class AddColorAction implements ActionListener {
        private Color color;
        private int code;

        public AddColorAction(Color color, int code) {
            this.color = color;
            this.code = code;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (farbcodes.size() < MAX_COLORS) {
                farbcodes.add(code);
                JLabel colorLabel = new JLabel();
                colorLabel.setOpaque(true);
                colorLabel.setBackground(color);
                colorLabel.setPreferredSize(new Dimension(50, 50));
                colorPanel.add(colorLabel);
                colorPanel.revalidate();
            } else {
                JOptionPane.showMessageDialog(PlayFrame.this, "Maximal 4 Farben erlaubt!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteColorAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!farbcodes.isEmpty()) {
                farbcodes.remove(farbcodes.size() - 1);
                colorPanel.remove(colorPanel.getComponentCount() - 1);
                colorPanel.revalidate();
                colorPanel.repaint();
            }
        }
    }

    private class SubmitColorsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] colorArray = farbcodes.stream().mapToInt(Integer::intValue).toArray();
            if (colorArray.length == 4) {
                strg.guess(colorArray);
                farbcodes.clear();
                colorPanel.removeAll();
                colorPanel.revalidate();
                colorPanel.repaint();
                updateHistory();
            } else {
                JOptionPane.showMessageDialog(PlayFrame.this, "Der Computer braucht 4 Farben zum spielen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
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
                hintField.setText("Richtige Position: " + board[i][4] + " Richtige Farbe: " + board[i][5]);
                rowPanel.add(hintField);

                gbc.gridy = i;
                boardPanel.add(rowPanel, gbc);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void updateHistory() {
        board = strg.getHistory();
        updateBoardPanel();
    }

    public void update() {
        updateHistory();
    }
}
