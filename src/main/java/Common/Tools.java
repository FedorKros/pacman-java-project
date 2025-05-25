package Common;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tools {

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
        return button;
    }
}
