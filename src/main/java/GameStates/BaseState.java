package GameStates;
import Common.Constants;
import javax.swing.*;

public abstract class BaseState extends JPanel {

    public BaseState() {
        setPreferredSize(Constants.WINDOW_SIZE);
        setLayout(null);
    }

    public abstract void processInput();
    public abstract void update();
    public abstract void draw();

}
