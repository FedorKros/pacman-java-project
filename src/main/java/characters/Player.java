package characters;

import common.Constants;

import java.awt.*;


public class Player extends Character {


    public Player(int x, int y, int[][] gameMap) {
        super(x, y, gameMap);
        direction = '0';
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x* Constants.TILE_SIZE, y* Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }


    @Override
    public void move() {
        int nextX = x;
        int nextY = y;

        switch (direction) {
            case 'u' -> nextY = y-1;
            case 'd' -> nextY = y+1;
            case 'l' -> nextX = x-1;
            case 'r' -> nextX = x+1;
        }

        if (gameMap[nextY][nextX] != 1) {
            setPos(nextX, nextY);
        }
    }

    public void setDirection(char d) {

        switch (d) {
            case 'u' -> {
                if (gameMap[y-1][x] == 0) direction = d;
            }
            case 'd' -> {
                if (gameMap[y+1][x] == 0) direction = d;
            }
            case 'l' -> {
                if (gameMap[y][x-1] == 0) direction = d;
            }
            case 'r' -> {
                if (gameMap[y][x+1] == 0) direction = d;
            }
        }

    }
}
