package characters;

import bonuses.Bonus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Player extends Character {


    int score = 0;

    long bonusTime = 4000;
    long bonusTimeEnd = System.currentTimeMillis();

    boolean underBonusEffect = false;
    boolean canTakeDamage = true;
    Bonus.BonusType appliedBonus;
    boolean isHunting = false;
    double scoreMultiplier = 1;


    public Player(int x, int y, int[][] gameMap) {
        super(x, y, gameMap);
        direction = 'r';

        try {
            loadImages();
            launchAnimationThread();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load pacman animation images!" + e);
        }
    }

    @Override
    protected void loadImages() throws IOException {
        if (direction == '0') direction = 'r';
        try {
            animationImages = new BufferedImage[]{
                    ImageIO.read(new File("assets/animations/pacman/" + direction + "/p1.png")),
                    ImageIO.read(new File("assets/animations/pacman/" + direction + "/p2.png")),
                    ImageIO.read(new File("assets/animations/pacman/" + direction + "/p3.png")),
                    ImageIO.read(new File("assets/animations/pacman/" + direction + "/p4.png"))
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getAnimationImage() {
        return animationImages[currentImage];
    }

    public int getScore() {
        return score;
    }

    public boolean canTakeDamage() {
        return canTakeDamage;
    }

    public boolean isHunting() {
        return isHunting;
    }


    @Override
    public void move() {
        long now = System.currentTimeMillis();

        if (now - lastStepTime >= stepCooldown) {
            int nextX = x;
            int nextY = y;

            switch (direction) {
                case 'u' -> nextY = y - 1;
                case 'd' -> nextY = y + 1;
                case 'l' -> nextX = x - 1;
                case 'r' -> nextX = x + 1;
            }

            if (gameMap[nextY][nextX] != 1) {
                setPos(nextX, nextY);
            }
            lastStepTime = now;
        }
        if (now >= bonusTimeEnd) {
            stopBonusEffect();
        }
    }

    public void setDirection(char d) throws IOException {
        char dirBefore = direction;
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
        char dirAfter = direction;
        if (dirBefore != dirAfter) {
            loadImages();
        }
    }

    public void increaseScore() {
        score += (int) (10 * scoreMultiplier);
    }

    public void bonusBase(Bonus.BonusType type) {
        stopBonusEffect();
        underBonusEffect = true;
        appliedBonus = type;
        bonusTimeEnd = System.currentTimeMillis() + bonusTime;
    }

    public void bonusSpeed() {
        bonusBase(Bonus.BonusType.SPEED_BOOSTER);
        stepCooldown = 200;
    }

    public void bonusImmortality() {
        bonusBase(Bonus.BonusType.IMMORTALITY);
        canTakeDamage = false;
    }

    public void bonusHunter() {
        bonusBase(Bonus.BonusType.HUNTER);
        isHunting = true;
    }

    public void bonusDoubleScore() {
        bonusBase(Bonus.BonusType.DOUBLE_SCORE);
        scoreMultiplier = 2;
    }

    public void stopBonusEffect() {
        if (appliedBonus != null) {
            switch (appliedBonus) {
                case Bonus.BonusType.IMMORTALITY -> canTakeDamage = true;
                case Bonus.BonusType.SPEED_BOOSTER -> stepCooldown = 300;
                case Bonus.BonusType.DOUBLE_SCORE -> scoreMultiplier = 1;
                case Bonus.BonusType.HUNTER -> isHunting = false;
            }
            underBonusEffect = false;
        }
    }

    public String currentBonus() {
        if (underBonusEffect) {
            return appliedBonus.toString();
        }
        return "None";
    }
}
