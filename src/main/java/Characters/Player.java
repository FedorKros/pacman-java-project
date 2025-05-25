package Characters;

import Common.Constants;

import java.awt.*;

public class Player  {
    private int x, y;
    // u d l r are directions
    private char direction;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveBy(int horizontalSpeed, int verticalSpeed) {
        int nextX = x+horizontalSpeed;
        int nextY = y+verticalSpeed;
        if (Constants.SMALL_MAP[nextY][nextX] != 1) {
            setPos(nextX, nextY);
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
