package buttons;

import common.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetupButton {
    public static JButton setupButton(String text, JPanel panel) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        button.addActionListener((ActionListener) panel);
        applyDesign(button);
        button.setFocusable(false);
        return button;
    }

    public static JButton setupButtonCenter(String text, JPanel panel, int y) {
        JButton button = new JButton(text);
        int x = (Constants.WINDOW_SIZE.width - Constants.BUTTON_WIDTH)/2;
        button.setBounds(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        panel.add(button);
        button.addActionListener((ActionListener) panel);
        applyDesign(button);
        button.setFocusable(false);
        return button;
    }

    public static JButton setupMapButton(String text, JPanel actionListenerPanel) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener((ActionListener) actionListenerPanel);
//        button.setBounds(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT*4);
        button.setPreferredSize(Constants.MAP_BUTTON_SIZE);
        applyDesign(button);
        return button;
    }

    public static void applyDesign(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setForeground(Constants.BUTTON_TEXT_COLOR);
        button.setFont(Constants.FONT_NORMAL);
        button.setBorder(new LineBorder(Color.CYAN));
    }
}
