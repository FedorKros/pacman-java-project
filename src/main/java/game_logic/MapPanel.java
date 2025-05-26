package game_logic;

import characters.Player;
import common.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapPanel extends JPanel {
    private int[][] gameMap;
    Player player;

    public MapPanel(int[][] gameMap, Player player) {
        this.gameMap = gameMap;
        setPreferredSize(Constants.MAP_SIZE);
        setFocusable(true);

        this.player = player;

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed: " + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.moveBy(1, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.moveBy(-1, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.moveBy(0, -1);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.moveBy(0, 1);
                }
                repaint();

            }


            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < Constants.SMALL_MAP.length; i++) {
            for (int j = 0; j < Constants.SMALL_MAP.length; j++) {
                switch (gameMap[i][j]) {
                    case 0 -> g.setColor(Color.BLACK);
                    case 1 -> g.setColor(Color.PINK);
                }
                g.fillRect(j * Constants.SMALL_TILE_SIZE, i * Constants.SMALL_TILE_SIZE, Constants.SMALL_TILE_SIZE, Constants.SMALL_TILE_SIZE);
            }
        }
        player.draw(g);

    }
}
