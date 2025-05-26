package buttons;

import common.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetupButton {
    public static JButton setupButton(String text, JPanel panel, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        panel.add(button);
        button.addActionListener((ActionListener) panel);
        button.setFocusPainted(false);

        return button;
    }

    public static JButton setupButtonCenter(String text, JPanel panel, int y) {
        JButton button = new JButton(text);
        int x = (Constants.WINDOW_SIZE.width - Constants.BUTTON_WIDTH)/2;
        button.setBounds(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        panel.add(button);
        button.addActionListener((ActionListener) panel);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setForeground(Color.ORANGE);
        button.setFont(Constants.FONT_NORMAL);
        button.setBorder(new LineBorder(Color.CYAN));

        return button;
    }
}
