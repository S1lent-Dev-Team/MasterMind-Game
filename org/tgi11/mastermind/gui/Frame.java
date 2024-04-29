package org.tgi11.mastermind.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    private Key key;

    public Frame(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Game");
        final JFrame theFrame = this;
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(new Dimension(100,100));
        setPreferredSize(new Dimension(1280,720));
        setSize(getPreferredSize());
        setUndecorated(false);
        key = new Key(this);
        addKeyListener(key);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
