package game_states;

import buttons.MouseHover;
import buttons.SetupButton;
import common.Tools;
import game_logic.PacmanGUI;
import scores.Score;
import scores.ScoreComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class ScoresState extends BaseState {
    JLabel label;
    JPanel scorePanel;
    ArrayList<Score> scores;
    JButton menuButton;
    JPanel buttonPanel, scrollPanel;

    public ScoresState(PacmanGUI gui) throws IOException {
        super(gui);



//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        titleLabel.setText("Highest scores");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

//        add(Box.createRigidArea(new Dimension(10, 10)));
        add(titleLabel, BorderLayout.NORTH);
//        add(Box.createRigidArea(new Dimension(10, 10)));

        scores = Tools.loadScores();

        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        menuButton = SetupButton.setupButton("Menu", this);
        buttonPanel = new JPanel();
        scrollPanel = new JPanel();
//        scrollPanel.setLayout(new BorderLayout(5, 5));
        for (Score score : scores) {
            ScoreComponent line = new ScoreComponent(score.getScore(), score.getNickname(),score.getPlayTime(), score.isWon(), score.getMapSize());
            scorePanel.add(line);
        }


        JScrollPane scrollPane = new JScrollPane(scorePanel);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setPreferredSize(new Dimension(900,600));
//        scrollPane.setMaximumSize(new Dimension(600,500));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // speed up the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        scrollPanel.setBackground(Color.BLACK);
        scrollPanel.add(scrollPane);

        menuButton.setHorizontalAlignment(SwingConstants.CENTER);
        menuButton.addMouseListener(new MouseHover());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(menuButton);

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
