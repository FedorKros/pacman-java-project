package scores;

import common.Constants;

import javax.swing.*;
import java.awt.*;

public class ScoreComponent extends JPanel {
    public JLabel scoreLabel, nicknameLabel, timeLabel, wonLabel, mapLabel;

    public ScoreComponent(int score, String nickname, int playTime, boolean won, int mapSize) {
        scoreLabel = new JLabel(String.valueOf(score));
        nicknameLabel = new JLabel(nickname);
        timeLabel = new JLabel(String.valueOf(playTime));
        wonLabel = new JLabel(String.valueOf(won));
        mapLabel = new JLabel("Map size: " + mapSize);


        this.setLayout(new GridLayout(1,5,10,0));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));

        add(scoreLabel);
        add(nicknameLabel);
        add(timeLabel);
        add(wonLabel);
        add(mapLabel);

        for (Component component : this.getComponents()) {
            if (component instanceof JLabel) {
                component.setBackground(Color.BLACK);
                component.setForeground(Constants.BUTTON_TEXT_COLOR);
                component.setFont(Constants.FONT_NORMAL);
            }
        }

    }



}
