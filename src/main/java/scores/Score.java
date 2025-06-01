package scores;

import common.Constants;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
    private int score;
    private String nickname;
    private int playTime;
    private boolean won;
    private int mapSize;

    public Score(int score, String nickname, int playTime, boolean won, int mapLength) {
        this.score = score;
        this.nickname = nickname;
        this.playTime = playTime;
        this.won = won;

        if (mapLength == Constants.SMALL_MAP.length) this.mapSize = 1;
        else if (mapLength == Constants.MEDIUM_MAP.length) this.mapSize = 2;
        else this.mapSize = 3;
    }

    public int getScore() {
        return score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayTime() {
        return playTime;
    }

    @Override
    public int compareTo(Score o) {
        return this.score - o.score;
    }
}
