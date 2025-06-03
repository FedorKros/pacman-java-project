package game_states;

import buttons.MouseHover;
import buttons.SetupButton;
import common.Constants;
import common.Tools;
import game_logic.PacmanGUI;
import scores.Score;
import scores.ScoreComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ScoresState extends BaseState {
    JLabel label;
    JPanel scorePanel, legendPanel, topPanel;
    ArrayList<Score> scores;
    JButton menuButton;
    JPanel buttonPanel, scrollPanel;

    public ScoresState(PacmanGUI gui) throws IOException {
        super(gui);

        setLayout(new BorderLayout());


        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        legendPanel = new JPanel();
        legendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        legendPanel.setOpaque(false);

        titleLabel.setText("Highest scores");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Score");
        JLabel nicknameLabel = new JLabel("Nickname");
        JLabel timeLabel = new JLabel("Playtime");
        JLabel wonLabel = new JLabel("Game status");
        JLabel mapLabel = new JLabel("Map size");

        legendPanel.add(scoreLabel);
        legendPanel.add(nicknameLabel);
        legendPanel.add(timeLabel);
        legendPanel.add(wonLabel);
        legendPanel.add(mapLabel);

        for (Component c : legendPanel.getComponents()) {
            c.setFont(Constants.FONT_NORMAL);
            c.setForeground(Constants.BUTTON_TEXT_COLOR);
        }




        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(legendPanel);





        scores = Tools.loadScores();
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));


        menuButton = SetupButton.setupButton("Menu", this);
        buttonPanel = new JPanel();
        scrollPanel = new JPanel();

        for (Score score : scores) {
            ScoreComponent line = new ScoreComponent(score.getScore(), score.getNickname(),score.getPlayTime(), score.isWon(), score.getMapSize());
            scorePanel.add(line);
        }


        JScrollPane scrollPane = new JScrollPane(scorePanel);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setPreferredSize(new Dimension(900,530));


        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // speed up the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.setBorder(new LineBorder(Color.WHITE, 2));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scorePanel.setOpaque(false);
        scrollPanel.setOpaque(false);


        scrollPanel.add(scrollPane);

        menuButton.setHorizontalAlignment(SwingConstants.CENTER);
        menuButton.addMouseListener(new MouseHover());

        buttonPanel.add(menuButton);
        buttonPanel.setOpaque(false);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    @Override
    public void setupUI() {


        for (Component c: this.getComponents()) {
            ((JComponent)c).setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gui.changeState(new MainMenuState(gui));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuButton) {
            gui.changeState(new MainMenuState(gui));
        }
    }
}
