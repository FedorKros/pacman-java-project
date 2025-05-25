package GameStates;

import Common.Tools;
import GameLogic.PacmanGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ScoresState extends BaseState {
    JLabel label;

    public ScoresState(PacmanGUI gui) {
        super(gui);

        label = new JLabel("Scores");
        add(label);
        label.setBounds(300,300, 300,50);

    }

//    @Override
//    public void processInput() {}
//    @Override
//    public void update() {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gui.changeState(new MainMenuState(gui));
        }
    }
}
