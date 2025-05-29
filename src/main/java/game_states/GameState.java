package game_states;
import characters.Enemy;
import characters.Player;
import common.Constants;
import game_logic.PacmanGUI;
import map_navigation.GraphMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static common.Constants.FRAME_LENGTH;

public class GameState extends BaseState {
    int[][] gameMap;
    Player player;
    Enemy enemy1, enemy2, enemy3, enemy4;
    boolean gameIsOn = true;


    public GameState(PacmanGUI gui, int mapNumber) {
        super(gui);

        switch (mapNumber) {
            case 1 -> gameMap = Constants.SMALL_MAP;
            case 2 -> gameMap = Constants.MEDIUM_MAP;
            case 3 -> gameMap = Constants.BIG_MAP;

        }

        List<List<Integer>> adj = GraphMap.createAdjList(gameMap);

        player = new Player(1,1, gameMap);
        enemy1 = new Enemy(2,1, gameMap);
        updateMap();

        Thread gameLoop = new Thread(() -> {
                while (gameIsOn) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    player.move();
                    SwingUtilities.invokeLater(this::updateMap);
                }

        });

        gameLoop.start();

    }

    @Override
    public void setupUI() {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            player.moveBy(1,0);
            player.setDirection('r');
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            player.moveBy(-1,0);
            player.setDirection('l');
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
//            player.moveBy(0,-1);
            player.setDirection('u');
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            player.moveBy(0,1);
            player.setDirection('d');
        }


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
                if (enemy1.getX() == j && enemy1.getY() == i) {
                    tile.setBackground(Color.RED);
                }


                c.gridx = j;
                c.gridy = i;

                add(tile, c);
            }
        }

        revalidate();
        repaint();

    }

    @Override
    public void tick() {
        player.moveBy(0,0);
    }

}
