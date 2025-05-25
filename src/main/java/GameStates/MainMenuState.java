package GameStates;
import GameLogic.PacmanGUI;
import javax.swing.JButton;
import Common.Tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenuState extends BaseState {
    JButton playButton, scoresButton, quitButton;


    public MainMenuState(PacmanGUI gui) {
        super(gui);

        playButton = Tools.setupButtonCenter("New Game", this, 100);
        scoresButton = Tools.setupButtonCenter("Highest scores", this, 200);
        quitButton = Tools.setupButtonCenter("Exit", this, 300);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            gui.changeState(new GameState(gui));
        } else if (e.getSource() == scoresButton) {
            gui.changeState(new ScoresState(gui));
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }




    }

