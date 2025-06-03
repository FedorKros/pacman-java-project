package game_states;
import buttons.MouseHover;
import buttons.SetupButton;
import characters.Enemy;
import characters.Player;
import common.Constants;
import common.Tools;
import game_logic.PacmanGUI;
import map_navigation.GraphMap;
import scores.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameState extends BaseState {
    int[][] gameMap;
    Player player;
    Enemy enemy1, enemy2, enemy3, enemy4;
    boolean gameIsOn = true;
    boolean lost = false;
    ArrayList<Enemy> enemies;
    List<List<Integer>> adj;
    List<Integer> visited = new ArrayList<Integer>();
    int score = 0;
    int maxScore = 0;
    int lives = 3;
    JLabel scoreLabel, livesLabel, timeLabel;
    JPanel boardPanel = new JPanel();
    long startTime;
    JFrame window;

    public GameState(PacmanGUI gui, int mapNumber, JFrame window) {
        super(gui);
        this.window = window;
        setLayout(new BorderLayout());

        startTime = System.currentTimeMillis();

        switch (mapNumber) {
            case 1 -> gameMap = Constants.SMALL_MAP;
            case 2 -> gameMap = Constants.MEDIUM_MAP;
            case 3 -> gameMap = Constants.BIG_MAP;

        }

        initVisited();
        countMaximumScore();

        int enemy1X = gameMap.length-2;
        int enemy1Y = gameMap.length-2;
        int enemy2X = gameMap.length-2;
        int enemy2Y = 1;
        int enemy3X = 1;
        int enemy3Y = gameMap.length-2;


        adj = GraphMap.createAdjList(gameMap);

        player = new Player(1,1, gameMap);
        enemy1 = new Enemy(enemy1X,enemy1Y, gameMap, player, true);
        enemy2 = new Enemy(enemy2X, enemy2Y, gameMap, player);
        enemy3 = new Enemy(enemy3X, enemy3Y, gameMap, player);
        enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);



        scoreLabel = new JLabel("Score: " + score + "/" + maxScore);
        scoreLabel.setFont(Constants.FONT_NORMAL);
        scoreLabel.setForeground(Constants.BUTTON_TEXT_COLOR);

        livesLabel = new JLabel("HP: " + lives);
        livesLabel.setFont(Constants.FONT_NORMAL);
        livesLabel.setForeground(Constants.BUTTON_TEXT_COLOR);

        timeLabel = new JLabel("Time: " + 0);
        timeLabel.setFont(Constants.FONT_NORMAL);
        timeLabel.setForeground(Constants.BUTTON_TEXT_COLOR);

        JPanel interfacePanel = new JPanel();
        interfacePanel.setBackground(Color.BLACK);
        interfacePanel.setForeground(Color.WHITE);
        interfacePanel.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));
        interfacePanel.add(scoreLabel, BorderLayout.NORTH);
        interfacePanel.add(livesLabel, BorderLayout.NORTH);
        interfacePanel.add(timeLabel, BorderLayout.NORTH);


        JPanel interfaceWrapperPanel = new JPanel();
        interfaceWrapperPanel.setPreferredSize(new Dimension(150,0));
        interfaceWrapperPanel.setLayout(new BoxLayout(interfaceWrapperPanel, BoxLayout.Y_AXIS));
        interfaceWrapperPanel.setBackground(Color.BLACK);
        interfaceWrapperPanel.add(interfacePanel);
        interfaceWrapperPanel.add(livesLabel, BorderLayout.NORTH);




        add(interfaceWrapperPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        boardPanel.setFocusable(true);
        boardPanel.requestFocus();

        updateMap();

        Thread gameLoop = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(Constants.GAME_FRAME_LENGTH);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (!gameIsOn) continue;

                    player.move();
                    enemiesHunt();

                    SwingUtilities.invokeLater(this::updateInterfaceLabels);
                    SwingUtilities.invokeLater(this::updateMap);


                }
        });

        gameLoop.start();

    }

    @Override
    public void setupUI() {

    }

    public void countMaximumScore() {
        maxScore = 0;
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap.length; j++) {
                if (gameMap[i][j] == 0) {
                    maxScore++;
                }
            }
        }

    }

    public int playTime() {
        long timePlayed = System.currentTimeMillis() - startTime;
        return (int)(timePlayed/1000);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char direction = ' ';
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> direction = 'u';
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> direction = 'd';
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> direction = 'l';
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> direction = 'r';
        }
        player.setDirection(direction);

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameIsOn = !gameIsOn;
//            SwingUtilities.getWindowAncestor(this).dispose();
        }


    }


//    public void

    public void initVisited() {
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap.length; j++) {
                visited.add(0);
            }
        }
    }


    public void updateInterfaceLabels() {
        scoreLabel.setText("Score: " + score + "/" + maxScore);
        livesLabel.setText("HP: " + lives);
        timeLabel.setText("Time: " + playTime());
    }



    public void updateMap() {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        boardPanel.setBackground(Color.BLACK);
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

                for (Enemy e : enemies) {
                    if (e.getX() == j && e.getY() == i) {
                        tile.setBackground(Color.RED);
                    }
                }
                c.gridx = j;
                c.gridy = i;

                boardPanel.add(tile, c);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();

    }

    public void enemiesHunt() {
        for (Enemy enemy : enemies) {
            if (enemy.isSmart()) enemy.chaseOptimally(adj, player);
            else enemy.chaseSilly(adj, player);
        if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) {
            if (!enemy.getInCooldown()) {
                lives -= 1;
                if (lives <= 0) {
                    lost = true;
                    gameOver();
                }
                enemy.cooldown();
            }
        }

    }}

    public void gameOver() {
        gameIsOn = false;
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = new JLabel();
        if (lost) gameOverLabel.setText("Game Over");
        else gameOverLabel.setText("You won!");
        gameOverLabel.setForeground(Constants.BUTTON_TEXT_COLOR);
        gameOverLabel.setFont(Constants.FONT_LARGE);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        this.add(new JLabel(String.valueOf(this.score)));
        JLabel scoreLabel = new JLabel("Your score: " + score);
        scoreLabel.setForeground(Constants.BUTTON_TEXT_COLOR);
        scoreLabel.setFont(Constants.FONT_NORMAL);

        JLabel enterNameLabel = new JLabel("Enter your nickname: ");
        enterNameLabel.setForeground(Constants.BUTTON_TEXT_COLOR);
        enterNameLabel.setFont(Constants.FONT_NORMAL);


        JTextField enterNamePane = new JTextField();
        enterNamePane.setEditable(true);
        enterNamePane.setBackground(Color.BLACK);
        enterNamePane.setBorder(BorderFactory.createLineBorder(Constants.POINT_COLOR,2, true));
        enterNamePane.setMaximumSize(new Dimension(200,30));
        enterNamePane.setFont(Constants.FONT_NORMAL);
        enterNamePane.setForeground(Constants.POINT_COLOR);



        JButton submitButton = SetupButton.setupButton("Record score", this);
        submitButton.addMouseListener(new MouseHover());

        submitButton.addActionListener(e -> {
            String nickname = enterNamePane.getText().trim();
            if (!nickname.isEmpty()) {
                try {
                    Tools.saveScore(new Score(score, nickname, playTime(), !lost, gameMap.length));
                    gui.changeState(new MainMenuState(gui));
                    window.dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        this.add(Box.createVerticalGlue());
        this.add(gameOverLabel); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        this.add(scoreLabel); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        this.add(enterNameLabel); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        this.add(enterNamePane); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        this.add(submitButton); add(Box.createRigidArea(Constants.MENU_BUTTON_VMARGIN));
        this.add(Box.createVerticalGlue());





        for (Component c: getComponents()) {
            if (c instanceof JComponent) {
                ((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }


        revalidate();
        repaint();
    }


}
