package characters;

import java.util.List;
import java.util.Queue;
import map_navigation.GraphMap;

public class Enemy extends Character {
    Player target;
    static int cooldownTimeMs = 4000;
    boolean inCooldown = false;
    boolean smart;


    public Enemy(int x, int y, int[][] gameMap, Player target) {
        super(x, y, gameMap);
        this.target = target;
        this.smart = false;

    }

    public Enemy(int x, int y, int[][] gameMap, Player target, boolean smart) {
        super(x, y, gameMap);
        this.target = target;
        this.smart = smart;
    }

    public void cooldown() {
        inCooldown = true;
        if (inCooldown) {
            new Thread(() -> {
                try {
                    Thread.sleep(cooldownTimeMs);
                }
                catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                inCooldown = false;
            }).start();
        }
    }

    public boolean getInCooldown() {
        return inCooldown;
    }

    public boolean isSmart() {
        return smart;
    }

    public void chaseOptimally(List<List<Integer>> adj, Player player) {
        long now = System.currentTimeMillis();
        if (!inCooldown && now - lastStepTime >= stepCooldown) {

            int[] distance = GraphMap.bfsFromPlayer(adj, player, gameMap.length);

            int curX = this.getX();
            int curY = this.getY();
            int minDistX = curX;
            int minDistY = curY;

            if (!player.isHunting) {

                if (gameMap[curY - 1][curX] == 0) {
                    if (distance[GraphMap.getCellNum(curY - 1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX;
                        minDistY = curY - 1;

                    }
                }
                if (gameMap[curY + 1][curX] == 0) {
                    if (distance[GraphMap.getCellNum(curY + 1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistY = curY + 1;
                        minDistX = curX;
                    }
                }
                if (gameMap[curY][curX - 1] == 0) {
                    if (distance[GraphMap.getCellNum(curY, curX - 1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX - 1;
                        minDistY = curY;
                    }
                }
                if (gameMap[curY][curX + 1] == 0) {
                    if (distance[GraphMap.getCellNum(curY, curX + 1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX + 1;
                        minDistY = curY;
                    }
                }

                if (gameMap[minDistY][minDistX] == 0) {
                    this.setPos(minDistX, minDistY);
                }
            }

            else {
                    if (gameMap[curY - 1][curX] == 0) {
                        if (distance[GraphMap.getCellNum(curY - 1, curX, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX;
                            minDistY = curY - 1;

                        }
                    }
                    if (gameMap[curY + 1][curX] == 0) {
                        if (distance[GraphMap.getCellNum(curY + 1, curX, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistY = curY + 1;
                            minDistX = curX;
                        }
                    }
                    if (gameMap[curY][curX - 1] == 0) {
                        if (distance[GraphMap.getCellNum(curY, curX - 1, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX - 1;
                            minDistY = curY;
                        }
                    }
                    if (gameMap[curY][curX + 1] == 0) {
                        if (distance[GraphMap.getCellNum(curY, curX + 1, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX + 1;
                            minDistY = curY;
                        }
                    }

                    if (gameMap[minDistY][minDistX] == 0) {
                        this.setPos(minDistX, minDistY);
                    }

            }
        lastStepTime = now;
    }
    }

    public void chaseSilly(List<List<Integer>> adj, Player player) {
        long now = System.currentTimeMillis();
        if (!inCooldown && now-lastStepTime >= stepCooldown) {
            int nextCellInd = GraphMap.callDfsFromEnemy(adj, player, gameMap.length, this);
            if (nextCellInd > 0) {
                int nextY = nextCellInd / gameMap.length;
                int nextX = nextCellInd % gameMap.length;
                setPos(nextX, nextY);
            }
            lastStepTime = now;
        }
    }



}
