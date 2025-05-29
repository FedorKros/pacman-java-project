package characters;

import java.util.Queue;

public class Enemy extends Character {

    public Enemy(int x, int y, int[][] gameMap) {
        super(x, y, gameMap);
    }

    public void chasePlayer(Player player) {
        int targetX = player.getX();
        int targetY = player.getY();

    }

    @Override
    public void move() {

    }


}
