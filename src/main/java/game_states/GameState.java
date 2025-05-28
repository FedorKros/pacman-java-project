package game_states;
import characters.Player;
import common.Constants;
import game_logic.MapPanel;
import game_logic.PacmanGUI;

import java.awt.event.KeyEvent;

public class GameState extends BaseState {
    MapPanel mapPanel;
    int[][] map;
    Player player;

    public GameState(PacmanGUI gui) {
        super(gui);
    }

    @Override
    public void setupUI() {
        setLayout(null);
        map = Constants.SMALL_MAP;
        player = new Player(1,1, this.map);

        mapPanel = new MapPanel(Constants.SMALL_MAP, player);
        mapPanel.setBounds(Constants.GAP_WIDTH, 0, Constants.WINDOW_SIZE.height, Constants.WINDOW_SIZE.height);
        add(mapPanel);
        mapPanel.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveBy(1,0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.moveBy(-1,0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.moveBy(0,-1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.moveBy(0,1);
        }
        repaint();

    }

}
