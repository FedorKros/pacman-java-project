package GameStates;
import Characters.Player;
import Common.Constants;
import GameLogic.MapPanel;
import GameLogic.PacmanGUI;
import javax.swing.*;
import java.awt.*;

public class GameState extends BaseState {
    MapPanel mapPanel;
    int[][] map;

    public GameState(PacmanGUI gui) {
        super(gui);
        setLayout(null);
        mapPanel = new MapPanel(Constants.SMALL_MAP);
        mapPanel.setBounds(Constants.GAP_WIDTH, 0, Constants.WINDOW_SIZE.height, Constants.WINDOW_SIZE.height);
        mapPanel.requestFocusInWindow();
        add(mapPanel);
        map = Constants.SMALL_MAP;

    }


}
