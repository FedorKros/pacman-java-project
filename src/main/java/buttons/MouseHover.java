package buttons;

import common.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHover extends MouseAdapter {

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
//        button.setBorderPainted(false);
        button.setBorder(new LineBorder(Color.CYAN, 4));
//        button.setOpaque(true);
//        button.setContentAreaFilled(true);
//        button.setFont(Constants.FONT_HOVER);
//        new Thread(() -> {
//            int opacity = 0;
//            button.setBackground(new Color(1,255,255, opacity));
//            while (opacity < 255) {
//                opacity++;
//                button.setBackground(new Color(1,255,255, opacity));
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }).start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
//        button.setBorderPainted(true);
        button.setBorder(new LineBorder(Color.CYAN));
//        button.setFont(Constants.FONT_NORMAL);
    }
}
