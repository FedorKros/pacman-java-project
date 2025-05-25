package GameStates;
import Common.Constants;
import GameLogic.PacmanGUI;

import javax.swing.*;
import java.awt.*;

public abstract class BaseState extends JPanel {
    PacmanGUI gui;


    public BaseState(PacmanGUI gui) {
        this.gui = gui;
        setLayout(null);
        setVisible(true);
        setPreferredSize(Constants.WINDOW_SIZE);

    }

    public abstract void processInput();
    public abstract void update();


}
