package buttons;

import common.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetupButton {
    public static JButton setupButton(String text, JPanel panel) {
        JButton button = new JButton(text);
        button.setPreferredSize(Constants.BUTTON_SIZE);
        button.setMaximumSize(Constants.BUTTON_SIZE);
        button.addActionListener((ActionListener) panel);
        applyDesign(button);
        return button;
    }

    public static JButton setupMapButton(String text, JPanel actionListenerPanel) {
        JButton button = new JButton(text);
        button.addActionListener((ActionListener) actionListenerPanel);
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
