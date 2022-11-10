package com.ikinsure;

import java.awt.*;

public class Spiral {

    public static final int RESOLUTION = 301;
    public static final int PIXEL_SIZE = 2;
    public static final int FRAME_SIZE = RESOLUTION * PIXEL_SIZE;
    public static final Color PRIME_COLOR = Color.BLUE;
    public static final Color NOT_PRIME_COLOR = Color.WHITE;
    public static final Color EMPTY_COLOR = Color.BLACK;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SpiralFrame frame = new SpiralFrame();
            frame.setSize(FRAME_SIZE, FRAME_SIZE);
            frame.setResizable(false);
            frame.setTitle("Ulam spiral");
            frame.setVisible(true);
            frame.pack();
            frame.runCalculation();
        });
    }

}
