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
        buttonsPanel.setBackground(Color.BLACK);

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
            System.out.println("Small Map");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gui.changeState(new MainMenuState(gui));
        }
    }




}
