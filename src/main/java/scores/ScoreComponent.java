package scores;

import common.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;


public class ScoreComponent extends JPanel {
    public JLabel scoreLabel, nicknameLabel, timeLabel, wonLabel, mapLabel;
    public static final Map<Integer, String> mapMap = Map.ofEntries(
            entry(1, Constants.SMALL_MAP_NAME),
            entry(2, Constants.MEDIUM_MAP_NAME),
            entry(3, Constants.BIG_MAP_NAME)
    );


    public ScoreComponent(int score, String nickname, int playTime, boolean won, int mapSize) {

        scoreLabel = new JLabel(String.valueOf(score));
        nicknameLabel = new JLabel(nickname);
        timeLabel = new JLabel(String.valueOf(playTime));
        wonLabel = new JLabel("Lost");
        if (won) wonLabel.setText("Won");
        mapLabel = new JLabel(mapMap.get(mapSize));

        this.setLayout(new GridLayout(1,5,10,0));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        this.setOpaque(false);
        this.setMaximumSize(Constants.SCORE_COMPONENT_SIZE);
        this.setPreferredSize(Constants.SCORE_COMPONENT_SIZE);

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
