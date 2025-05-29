package game_states;

import game_logic.PacmanGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ScoresState extends BaseState {
    JLabel label;

    public ScoresState(PacmanGUI gui) {
        super(gui);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    @Override
    public void setupUI() {
        titleLabel.setText("Highest scores");
        add(titleLabel);

        for (Component c: this.getComponents()) {
            ((JComponent)c).setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gui.changeState(new MainMenuState(gui));
        }
    }
}
