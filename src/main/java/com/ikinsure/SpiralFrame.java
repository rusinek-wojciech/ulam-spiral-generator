package com.ikinsure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SpiralFrame extends JFrame {

    public static final Color PRIME_COLOR = Color.BLACK;
    public static final Color EMPTY_COLOR = Color.WHITE;

    private final Color[][] pixels;

    private final Runnable runnable;
    public SpiralFrame(int size, int pixelSize) {
        final int resolution = size / pixelSize;

        this.pixels =  new Color[resolution + 2][resolution + 2];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels.length; j++) {
                this.pixels[i][j] = EMPTY_COLOR;
            }
        }

        final JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                final Graphics2D graph = (Graphics2D) g;

                for (int y = 0; y < pixels.length; y++) {
                    for (int x = 0; x < pixels.length; x++) {
                        var square = new Rectangle2D.Float(
                                x * pixelSize,
                                y * pixelSize,
                                Spiral.PIXEL_SIZE,
                                Spiral.PIXEL_SIZE
                        );
                        graph.setColor(pixels[y][x]);
                        graph.fill(square);
                        graph.draw(square);
                    }
                }
            }
        };

        add(panel);
        pack();

        this.runnable = () -> {
            final int bound = resolution * resolution + 1;
            final int middle = resolution / 2;

            int mainCounter = 1;
            int minCounter = 0;
            int stateCounter = 0;
            boolean flag = true;
            int x = middle;
            int y = middle;
            Direction direction = Direction.RIGHT;

            for (long value = 1; value < bound; value++) {
                if (minCounter >= mainCounter) {
                    if (flag) {
                        mainCounter++;
                    }
                    flag = !flag;
                    minCounter = 1;
                    direction = Direction.values()[stateCounter];
                    stateCounter++;
                    if (stateCounter == 4) {
                        stateCounter = 0;
                    }
                }
                minCounter++;
                x += direction.x;
                y += direction.y;

                if (Utilities.isPrime(value)) {
                    this.pixels[y][x] = PRIME_COLOR;
                    panel.repaint();
                }
            }
        };

    }

    public Runnable getRunnable() {
        return this.runnable;
    }
}
