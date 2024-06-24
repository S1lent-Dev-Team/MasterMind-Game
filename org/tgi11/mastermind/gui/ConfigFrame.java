package org.tgi11.mastermind.gui;

import org.tgi11.mastermind.Display;
import org.tgi11.mastermind.GUI;
import org.tgi11.mastermind.Steuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfigFrame extends JFrame {

    private ArrayList<Integer> farbcodes;
    private JPanel colorPanel;
    private JButton submitButton, deleteButton;
    private static final int MAX_COLORS = 4;
    private final Steuerung steuerung;
    private Display d;

    public ConfigFrame(Steuerung steuerung, Display display) {
        super("Farbwahl:");
        farbcodes = new ArrayList<>();
        d = display;
        this.steuerung = steuerung;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());
        add(colorPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 5, 5));

        String[] colorNames = {"Red", "Blue", "Yellow", "Green", "White", "Black", "Orange", "Brown"};
        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.BLACK, Color.ORANGE, new Color(165, 42, 42)};
        int[] colorCodes = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int i = 0; i < colorNames.length; i++) {
            JButton colorButton = new JButton(colorNames[i]);
            Color color = colors[i];
            int code = colorCodes[i];
            colorButton.setBackground(color);
            colorButton.addActionListener(new AddColorAction(color, code));
            buttonPanel.add(colorButton);
        }

        add(buttonPanel, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(new DeleteColorAction());
        controlPanel.add(deleteButton);

        submitButton = new JButton("Bestätigen");
        submitButton.addActionListener(new SubmitColorsAction());
        controlPanel.add(submitButton);

        add(controlPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
                JOptionPane.showMessageDialog(ConfigFrame.this, "Maximal 4 Farben erlaubt!", "Fehler", JOptionPane.ERROR_MESSAGE);
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
            if (colorArray.length == 4){
                submitColors(colorArray);
                dispose();
            }else {
                JOptionPane.showMessageDialog(ConfigFrame.this, "Der Computer braucht 4 Farben zum spielen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    protected void submitColors(int[] colors) {
        steuerung.setAnswer(colors);
        ((GUI)d).start = true;
    }
}



