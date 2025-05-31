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


public class GameState extends BaseState {
    int[][] gameMap;
    Player player;
    Enemy enemy1, enemy2, enemy3, enemy4;
    boolean gameIsOn = true;
    ArrayList<Enemy> enemies;
    List<List<Integer>> adj;
    List<Integer> visited = new ArrayList<Integer>();
    int score = 0;
    JLabel scoreLabel = new JLabel("Score: " + score);


    public GameState(PacmanGUI gui, int mapNumber) {
        super(gui);

        switch (mapNumber) {
            case 1 -> gameMap = Constants.SMALL_MAP;
            case 2 -> gameMap = Constants.MEDIUM_MAP;
            case 3 -> gameMap = Constants.BIG_MAP;

        }

        initVisited();


        adj = GraphMap.createAdjList(gameMap);
        player = new Player(1,1, gameMap);
        enemy1 = new Enemy(14,1, gameMap, player);
        enemies = new ArrayList<>();
        enemies.add(enemy1);

        add(scoreLabel, BorderLayout.WEST);
        updateMap();

        Thread gameLoop = new Thread(() -> {
                while (gameIsOn) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    player.move();

                    SwingUtilities.invokeLater(this::updateScoreLabel);
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
        char direction = ' ';
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> direction = 'u';
            case KeyEvent.VK_DOWN, KeyEvent.VK_D -> direction = 'd';
            case KeyEvent.VK_LEFT, KeyEvent.VK_S -> direction = 'l';
            case KeyEvent.VK_RIGHT, KeyEvent.VK_A -> direction = 'r';
        }
        player.setDirection(direction);
    }


    public void initVisited() {
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap.length; j++) {
                visited.add(0);
            }
        }
    }


    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }



    public void updateMap() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(Color.BLACK);
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {

                JPanel tile = new JPanel(new GridBagLayout());
                tile.setPreferredSize(Constants.SMALL_TILE_DIMENSION);
                if (gameMap[i][j] == 1) {
                    tile.setBackground(Constants.WALL_COLOR);
                    tile.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }
                else {
                    tile.setBackground(Color.BLACK);
                    if (visited.get(GraphMap.getCellNum(i, j, gameMap.length)) == 0) {
                        JPanel point = new JPanel();
                        point.setBackground(Constants.POINT_COLOR);
                        point.setPreferredSize(Constants.POINT_SIZE);
                        tile.add(point);
                    }
                }

                if (player.getX() == j && player.getY() == i) {

                    tile.removeAll();
                    tile.setBackground(Color.ORANGE);
                    if (visited.get(GraphMap.getCellNum(i, j, gameMap.length)) == 0) {
                        score++;
                        visited.set(GraphMap.getCellNum(i, j, gameMap.length), 1);
                    }
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
        enemy1.chaseOptimally(adj, player);
    }

}
