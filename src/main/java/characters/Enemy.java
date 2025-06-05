package characters;

import map_navigation.GraphMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class Enemy extends Character {
    Player target;
    private static final int cooldownTimeMs = 4000;
    private boolean inCooldown = false;
    private final boolean smart;

    private char prevDirection = '0';
    private final String name;


    public Enemy(int x, int y, int[][] gameMap, Player target, String name) {
        super(x, y, gameMap);
        this.target = target;
        this.name = name;
        this.smart = false;

        try {
            loadImages();
            launchAnimationThread();
            prevDirection = direction;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Enemy(int x, int y, int[][] gameMap, Player target, String name, boolean smart) {
        super(x, y, gameMap);
        this.target = target;
        this.name = name;
        this.smart = smart;

        try {
            loadImages();
            launchAnimationThread();
            prevDirection = direction;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void loadImages() throws IOException {
        try {
            if (direction != 'r' && direction != 'l' && direction != 'u' && direction != 'd') direction = 'r';
            animationImages = new BufferedImage[] {
                    ImageIO.read(new File("assets/animations/enemies/" + name + "/" + direction + "/p1.png")),
                    ImageIO.read(new File("assets/animations/enemies/" + name + "/" + direction + "/p2.png"))
            };
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public BufferedImage getAnimationImage() {
        return animationImages[currentImage];
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

            if (!player.isHunting()) {
                if (gameMap[curY - 1][curX] == 0) {
                    if (distance[GraphMap.getCellNum(curY - 1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX;
                        minDistY = curY - 1;
                        direction = 'u';
                    }
                }
                if (gameMap[curY + 1][curX] == 0) {
                    if (distance[GraphMap.getCellNum(curY + 1, curX, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistY = curY + 1;
                        minDistX = curX;
                        direction = 'd';
                    }
                }
                if (gameMap[curY][curX - 1] == 0) {
                    if (distance[GraphMap.getCellNum(curY, curX - 1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX - 1;
                        minDistY = curY;
                        direction = 'l';
                    }
                }
                if (gameMap[curY][curX + 1] == 0) {
                    if (distance[GraphMap.getCellNum(curY, curX + 1, gameMap.length)] < distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                        minDistX = curX + 1;
                        minDistY = curY;
                        direction = 'r';
                    }
                }

            }

            else {
                    if (gameMap[curY - 1][curX] == 0) {
                        if (distance[GraphMap.getCellNum(curY - 1, curX, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX;
                            minDistY = curY - 1;
                            direction = 'u';
                        }
                    }
                    if (gameMap[curY + 1][curX] == 0) {
                        if (distance[GraphMap.getCellNum(curY + 1, curX, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistY = curY + 1;
                            minDistX = curX;
                            direction = 'd';
                        }
                    }
                    if (gameMap[curY][curX - 1] == 0) {
                        if (distance[GraphMap.getCellNum(curY, curX - 1, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX - 1;
                            minDistY = curY;
                            direction = 'l';
                        }
                    }
                    if (gameMap[curY][curX + 1] == 0) {
                        if (distance[GraphMap.getCellNum(curY, curX + 1, gameMap.length)] > distance[GraphMap.getCellNum(curY, curX, gameMap.length)]) {
                            minDistX = curX + 1;
                            minDistY = curY;
                            direction = 'r';
                        }
                    }
            }

            if (gameMap[minDistY][minDistX] == 0) {
                this.setPos(minDistX, minDistY);
            }

            lastStepTime = now;
            if (prevDirection != direction) {
                try {
                    loadImages();
                    prevDirection = direction;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void chaseSilly(List<List<Integer>> adj, Player player) {
        long now = System.currentTimeMillis();
        if (!inCooldown && now-lastStepTime >= stepCooldown) {
            int nextCellInd = GraphMap.callDfsFromEnemy(adj, player, gameMap.length, this);
            if (nextCellInd > 0) {
                int nextY = nextCellInd / gameMap.length;
                int nextX = nextCellInd % gameMap.length;

                prevDirection = direction;

                if (nextX > x) direction = 'r';
                else if (nextX < x) direction = 'l';
                else if (nextY > y) direction = 'd';
                else if (nextY < y) direction = 'u';

                if (prevDirection != direction) {
                    try {
                        loadImages();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                setPos(nextX, nextY);
            }
            lastStepTime = now;
        }
    }

}
