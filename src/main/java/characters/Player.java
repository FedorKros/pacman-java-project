package characters;

import common.Constants;

import java.awt.*;

public class Player extends Character {


    public Player(int x, int y, int[][] gameMap) {
        super(x, y, gameMap);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x* Constants.TILE_SIZE, y* Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }





}
