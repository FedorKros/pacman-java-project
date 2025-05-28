package game_states;
import characters.Player;
import common.Constants;
import game_logic.PacmanGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends BaseState {
    int[][] gameMap;
    Player player;

    public GameState(PacmanGUI gui, int mapNumber) {
        super(gui);

        switch (mapNumber) {
            case 1 -> gameMap = Constants.SMALL_MAP;
            case 2 -> gameMap = Constants.MEDIUM_MAP;
            case 3 -> gameMap = Constants.BIG_MAP;

        }
        player = new Player(1,1, gameMap);
        updateMap();
    }

    @Override
    public void setupUI() {
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
        updateMap();

    }

    public void updateMap() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(Color.BLACK);
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {

                JPanel tile = new JPanel();
                tile.setPreferredSize(Constants.SMALL_TILE_DIMENSION);
                if (gameMap[i][j] == 1) {
                    tile.setBackground(Constants.WALL_COLOR);
                }
                else tile.setBackground(Color.BLACK);
                if (player.getX() == j && player.getY() == i) {
                    tile.setBackground(Color.ORANGE);
                }

                c.gridx = j;
                c.gridy = i;

                add(tile, c);
            }
        }

        revalidate();
        repaint();

    }

}
