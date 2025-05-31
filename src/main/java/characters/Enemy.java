package characters;

import java.util.List;
import java.util.Queue;
import map_navigation.GraphMap;

public class Enemy extends Character {
    Player target;


    public Enemy(int x, int y, int[][] gameMap, Player target) {
        super(x, y, gameMap);
        this.target = target;

    }

    public void chaseOptimally(List<List<Integer>> adj, Player player) {
        int[] distance = GraphMap.bfsFromPlayer(adj, player, gameMap.length);

            int curX = this.getX();
            int curY = this.getY();
            int minDistX = curX;
            int minDistY = curY;
            if (gameMap[curY-1][curX] == 0) {
                if (distance[GraphMap.getCellNum(curY-1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistX = curX;
                    minDistY = curY-1;

                }
            }
            if (gameMap[curY+1][curX] == 0) {
                if (distance[GraphMap.getCellNum(curY+1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistY = curY+1;
                    minDistX = curX;
                }
            }
            if (gameMap[curY][curX-1] == 0) {
                if (distance[GraphMap.getCellNum(curY, curX-1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistX = curX-1;
                    minDistY = curY;
                }
            }
            if (gameMap[curY][curX+1] == 0) {
                if (distance[GraphMap.getCellNum(curY, curX+1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                    minDistX = curX+1;
                    minDistY = curY;
                }
            }

            if (gameMap[minDistY][minDistX] == 0) {
                this.setPos(minDistX, minDistY);
            }

    }

    public void chaseRandomly(Player player) {
//        if
    }




}
