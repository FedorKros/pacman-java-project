package characters;

import java.util.Queue;
import map_navigation.GraphMap;

public class Enemy extends Character {
    Player target;


    public Enemy(int x, int y, int[][] gameMap, Player target) {
        super(x, y, gameMap);
        this.target = target;

    }

    public void chasePlayer(Player player) {
        int targetX = player.getX();
        int targetY = player.getY();

    }




}
