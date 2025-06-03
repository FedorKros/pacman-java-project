package bonuses;

public class Bonus {
    public enum BonusType { DOUBLE_SCORE, IMMORTALITY, HUNTER, SPEED_BOOSTER}

    BonusType type;
    int x,y;


    public Bonus(BonusType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public BonusType getType() {
        return type;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
