package bonuses;

public record Bonus(bonuses.Bonus.BonusType type, int x, int y) {
    public enum BonusType {DOUBLE_SCORE, IMMORTALITY, HUNTER, SPEED_BOOSTER}
}
