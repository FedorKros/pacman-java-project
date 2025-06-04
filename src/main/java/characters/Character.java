package characters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Character {
    int x, y;
    int[][] gameMap;

    char direction;
    int stepCooldown = 300;
    long lastStepTime = 0;

    BufferedImage[] animationImages;
    int currentImage = 0;
    int imageDuration = 200;

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


    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
    }

    protected void loadImages() throws IOException {}

    public void launchAnimationThread() {

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(imageDuration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (animationImages != null) {}
                currentImage = (currentImage + 1) % animationImages.length;
            }
        }).start();
    }
}
