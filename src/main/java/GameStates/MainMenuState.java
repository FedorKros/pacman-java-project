package GameStates;
import GameLogic.PacmanGUI;
import javax.swing.JButton;
import Common.Tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuState extends BaseState implements ActionListener {
    JButton playButton, scoresButton, quitButton;


    public MainMenuState(PacmanGUI gui) {
        super(gui);

        playButton = Tools.setupButton("New Game", this, 300, 100);
        scoresButton = Tools.setupButton("Highest scores", this, 300, 200);
        quitButton = Tools.setupButton("Exit", this, 300, 300);

    }

    @Override
    public void processInput() {}

    @Override
    public void update() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {

        }

        else if (e.getSource() == scoresButton) {
            gui.changeState(new ScoresState(gui));
        }

        else if (e.getSource() == quitButton) {
            System.exit(0);
        }

        }
    }

