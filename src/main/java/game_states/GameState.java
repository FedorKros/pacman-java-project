package game_states;
import characters.Enemy;
import characters.Player;
import common.Constants;
import game_logic.PacmanGUI;
import map_navigation.GraphMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static common.Constants.FRAME_LENGTH;

public class GameState extends BaseState {
    int[][] gameMap;
    Player player;
    Enemy enemy1, enemy2, enemy3, enemy4;
    boolean gameIsOn = true;
    ArrayList<Enemy> enemies;
    List<List<Integer>> adj;

    public GameState(PacmanGUI gui, int mapNumber) {
        super(gui);

        switch (mapNumber) {
            case 1 -> gameMap = Constants.SMALL_MAP;
            case 2 -> gameMap = Constants.MEDIUM_MAP;
            case 3 -> gameMap = Constants.BIG_MAP;

        }

        adj = GraphMap.createAdjList(gameMap);
        player = new Player(1,1, gameMap);
        enemy1 = new Enemy(14,1, gameMap, player);
        enemies = new ArrayList<>();
        enemies.add(enemy1);

        updateMap();

        Thread gameLoop = new Thread(() -> {
                while (gameIsOn) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    player.move();
                    enemiesHunt();

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

    public void enemiesHunt() {

//        int targetInd = GraphMap.getCellNum(player.getY(), player.getX(), gameMap.length);
        int[] distance = GraphMap.bfsFromPlayer(adj, player, gameMap.length);

        for (Enemy enemy: enemies) {
            int curX = enemy.getX();
            int curY = enemy.getY();

            int minDistX = curX;
            int minDistY = curY;

            if (gameMap[curY-1][curX] == 0) {
                if (distance[GraphMap.getCellNum(curY-1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistY = curY-1;

                }
            }

            if (gameMap[curY+1][curX] == 0) {
                if (distance[GraphMap.getCellNum(curY+1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistY = curY+1;
                }
            }

            if (gameMap[curY][curX-1] == 0) {
                if (distance[GraphMap.getCellNum(curY, curX-1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistX = curX-1;
                }
            }
            if (gameMap[curY][curX+1] == 0) {
                if (distance[GraphMap.getCellNum(curY, curX+1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistX = curX+1;
                }
            }

            if (gameMap[minDistY][minDistX] == 0) {
                enemy.setPos(minDistX, minDistY);
            }
        }
    }

}
