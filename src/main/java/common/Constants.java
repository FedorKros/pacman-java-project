package common;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Constants {
    public static final Dimension WINDOW_SIZE = new Dimension(1200,800);

    public static final int GAP_WIDTH = (WINDOW_SIZE.width - WINDOW_SIZE.height)/2;
    public static final int TITLEBAR_HEIGHT = 30;

    public static final Dimension TITLE_LABEL_SIZE = new Dimension(211,50);




//    public static final Font FONT = new Font("assets/fonts/Jersey-Regular.ttf", Font.PLAIN, 18);
    public static final Font FONT_NORMAL, FONT_LARGE;

    static {
        try {
            FONT_NORMAL = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Jersey-Regular.ttf")).deriveFont(Font.PLAIN, 26);
//            FONT_HOVER = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Jersey-Regular.ttf")).deriveFont(Font.BOLD, 26);
            FONT_LARGE = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Jersey-Regular.ttf")).deriveFont(Font.PLAIN, 74);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Game loop configuration
    public static final int FPS = 60;
    public static final int FRAME_LENGTH = 1000 / FPS;

    // Button size configuration
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;

    // Game "tiles" configuration
    public static final int[][] SMALL_MAP = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };


    public static final int SMALL_TILE_SIZE = (WINDOW_SIZE.height-TITLEBAR_HEIGHT)/SMALL_MAP.length;
    public static final Dimension MAP_SIZE = new Dimension(WINDOW_SIZE.height, WINDOW_SIZE.height);


    }


