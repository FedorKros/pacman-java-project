package game_states;
import common.Constants;
import game_logic.PacmanGUI;

import javax.net.ssl.SSLContextSpi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public abstract class BaseState extends JPanel implements ActionListener, KeyListener {
    PacmanGUI gui;
    JLabel titleLabel;

    public BaseState(PacmanGUI gui) {
        this.gui = gui;
        setLayout(null);
        setVisible(true);
        setPreferredSize(Constants.WINDOW_SIZE);
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);

        titleLabel = new JLabel();
        titleLabel.setForeground(Constants.TITLE_COLOR);
        titleLabel.setFont(Constants.FONT_LARGE);


        setupUI();
    }

    abstract public void setupUI();

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/background1.png").getImage(), 0, 0, null);
    }

}
