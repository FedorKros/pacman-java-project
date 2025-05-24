package GameStates;
import Common.Constants;
import GameLogic.PacmanGUI;

import javax.swing.*;

public abstract class BaseState extends JPanel {
    PacmanGUI gui;



    public BaseState() {
        setPreferredSize(Constants.WINDOW_SIZE);
        setLayout(null);
    }

    public abstract void processInput();
    public abstract void update();
    public abstract void draw();

}
