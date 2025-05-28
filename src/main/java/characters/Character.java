package characters;

import common.Constants;

import java.awt.*;

public class Character {
    int x, y;
    int[][] gameMap;
    // u d l r are directions
    private char direction;


    public Character(int x, int y, int[][] gameMap) {
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x* Constants.TILE_SIZE, y* Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void moveBy(int horizontalSpeed, int verticalSpeed) {
        int nextX = this.x+horizontalSpeed;
        int nextY = this.y+verticalSpeed;
        if (gameMap[nextY][nextX] != 1) {
            setPos(nextX, nextY);
        }
    }
}
