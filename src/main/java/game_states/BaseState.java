package game_states;
import common.Constants;
import game_logic.PacmanGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public abstract class BaseState extends JPanel implements ActionListener, KeyListener {
    PacmanGUI gui;


    public BaseState(PacmanGUI gui) {
        this.gui = gui;
        setLayout(null);
        setVisible(true);
        setPreferredSize(Constants.WINDOW_SIZE);
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
    }

//    public abstract void processInput();
//    public abstract void update();

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
