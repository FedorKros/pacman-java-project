package Common;

import java.awt.*;

public class Constants {
    public static final Dimension WINDOW_SIZE = new Dimension(1200,800);

    public static final int GAP_WIDTH = (WINDOW_SIZE.width - WINDOW_SIZE.height)/2;
    public static final int TITLEBAR_HEIGHT = 30;

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
    public static final Dimension MAP_SIZE = new Dimension(WINDOW_SIZE.height,WINDOW_SIZE.height);

    }


