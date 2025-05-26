package characters;

import common.Constants;

import java.awt.*;

public class Player  {
    private int x, y;
    int[][] gameMap;
    // u d l r are directions
    private char direction;

    public Player(int x, int y, int[][] gameMap) {
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    public void moveBy(int horizontalSpeed, int verticalSpeed) {
        int nextX = this.x+horizontalSpeed;
        int nextY = this.y+verticalSpeed;
        if (gameMap[nextY][nextX] != 1) {
            setPos(nextX, nextY);
            System.out.println("Moved to " + nextX + "," + nextY);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x* Constants.SMALL_TILE_SIZE, y* Constants.SMALL_TILE_SIZE, Constants.SMALL_TILE_SIZE, Constants.SMALL_TILE_SIZE);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
