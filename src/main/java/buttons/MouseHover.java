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
//        new Thread(() -> {
//            for (int alpha = 0; alpha <= 255; alpha += 15) {
//                final int a = alpha;
//
//                SwingUtilities.invokeLater(() -> {
//                    Color glow = new Color(0, 255, 204, a); // Neon cyan with fading in alpha
//                    button.setBorder(new LineBorder(glow, 6));
//                    button.repaint();
//                });
//
//                try {
//                    Thread.sleep(30); // Adjust for speed
//                } catch (InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                    break;
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
