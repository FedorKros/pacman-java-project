package game_states;
import buttons.MouseHover;
import buttons.SetupButton;
import common.Constants;
import game_logic.PacmanGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainMenuState extends BaseState {
    JButton playButton, scoresButton, quitButton;
    JLabel titleLabel;

    public MainMenuState(PacmanGUI gui) {
        super(gui);

        titleLabel = new JLabel("Pacman");
        titleLabel.setFont(Constants.FONT_LARGE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(
                (Constants.WINDOW_SIZE.width - Constants.TITLE_LABEL_SIZE.width)/2,
                100,
                Constants.TITLE_LABEL_SIZE.width,
                Constants.TITLE_LABEL_SIZE.height);
        add(titleLabel);



        playButton = SetupButton.setupButtonCenter("New Game", this, 250);
        scoresButton = SetupButton.setupButtonCenter("Highest Scores", this, 350);
        quitButton = SetupButton.setupButtonCenter("Exit", this, 450);

//        https://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
//        https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html

        playButton.addMouseListener(new MouseHover());
        scoresButton.addMouseListener(new MouseHover());
        quitButton.addMouseListener(new MouseHover());



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

