package com.ikinsure;

import java.awt.*;

public class Spiral {

    public static final int SIZE = 800;
    public static final int PIXEL_SIZE = 2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SpiralFrame frame = new SpiralFrame(SIZE, PIXEL_SIZE);
            frame.setSize(SIZE, SIZE);
            frame.setResizable(false);
            frame.setTitle("Ulam spiral");
            frame.setVisible(true);
            Thread thread = new Thread(frame.getRunnable());
            thread.start();
        });
    }

}
