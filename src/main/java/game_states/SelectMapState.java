package game_states;

import buttons.MouseHover;
import buttons.SetupButton;
import common.Constants;
import game_logic.PacmanGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectMapState extends BaseState {

    JButton smallMap, medMap, bigMap;
    JPanel buttonsPanel;

    public SelectMapState(PacmanGUI gui) {
        super(gui);
    }

    @Override
    public void setupUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel.setText("Select Map");

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonsPanel = new JPanel();

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Constants.MAP_BUTTON_HMARGIN,100));
        buttonsPanel.setOpaque(false);

        smallMap = SetupButton.setupMapButton("Bitvoid",this);
        medMap = SetupButton.setupMapButton("Neon Sprawl",this);
        bigMap = SetupButton.setupMapButton("Cyberspace",this);

        smallMap.addMouseListener(new MouseHover());
        medMap.addMouseListener(new MouseHover());
        bigMap.addMouseListener(new MouseHover());

        buttonsPanel.add(smallMap);
        buttonsPanel.add(medMap);
        buttonsPanel.add(bigMap);


        // https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(buttonsPanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == smallMap) {
            createGameWindow(gui, 1);
            System.out.println("Small Map");

        }
        if (e.getSource() == medMap) {
            createGameWindow(gui, 2);
            System.out.println("Medium Map");
        }
        if (e.getSource() == bigMap) {
            createGameWindow(gui, 3);
            System.out.println("Big Map");
        }
    }


    public static void createGameWindow(PacmanGUI gui, int mapNumber) {

        String windowName = "";
        switch (mapNumber) {
            case 1 -> windowName = " [Bitvoid]";
            case 2 -> windowName = " [Neon Sprawl]";
            case 3 -> windowName = " [Cyberspace]";
        }

        JFrame gameMapWindow = new JFrame("CyberPacMan" + windowName);
        GameState gamePanel = new GameState(gui, mapNumber, gameMapWindow);
        gameMapWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameMapWindow.setSize(Constants.WINDOW_SIZE.height, Constants.WINDOW_SIZE.height);
        gameMapWindow.setLocationRelativeTo(null);
        gameMapWindow.setResizable(false);
        gameMapWindow.setContentPane(gamePanel);
        gameMapWindow.setVisible(true);
        gameMapWindow.pack();


    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gui.changeState(new MainMenuState(gui));
        }
    }




}
