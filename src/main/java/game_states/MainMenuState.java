package game_states;
import buttons.MouseHover;
import buttons.SetupButton;
import common.Constants;
import game_logic.PacmanGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainMenuState extends BaseState {
    JButton playButton, scoresButton, quitButton;


    public MainMenuState(PacmanGUI gui) {
        super(gui);
    }

    @Override
    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        titleLabel.setText("CyberPacMan");

        playButton = SetupButton.setupButton("New Game", this);
        scoresButton = SetupButton.setupButton("Highest Scores", this);
        quitButton = SetupButton.setupButton("Exit", this);

//        https://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
//        https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html
        playButton.addMouseListener(new MouseHover());
        scoresButton.addMouseListener(new MouseHover());
        quitButton.addMouseListener(new MouseHover());


        add(Box.createVerticalGlue());
        add(titleLabel); add(Box.createRigidArea(Constants.MENU_TITLE_VMARGIN));
        add(playButton); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        add(scoresButton); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        add(quitButton); add(Box.createVerticalGlue());

        for (Component c: getComponents()) {
            if (c instanceof JComponent) {
                ((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
//            gui.changeState(new GameState(gui));
            gui.changeState(new SelectMapState(gui));
        } else if (e.getSource() == scoresButton) {
            try {
                gui.changeState(new ScoresState(gui));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }





    }

