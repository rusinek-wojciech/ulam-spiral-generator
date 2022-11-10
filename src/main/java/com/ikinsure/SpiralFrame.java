package com.ikinsure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ikinsure.Spiral.*;

public class SpiralFrame extends JFrame {

    private final Color[][] pixels;
    private final JPanel panel;
    private final ExecutorService calculationService;

    public SpiralFrame() {
        this.pixels =  new Color[RESOLUTION][RESOLUTION];
        this.calculationService = Executors.newSingleThreadExecutor();
        this.panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                final Graphics2D graph = (Graphics2D) g;
                for (int y = 0; y < pixels.length; y++) {
                    for (int x = 0; x < pixels.length; x++) {
                        var square = new Rectangle2D.Float(
                                x * PIXEL_SIZE,
                                y * PIXEL_SIZE,
                                PIXEL_SIZE,
                                PIXEL_SIZE
                        );
                        graph.setColor(pixels[y][x]);
                        graph.fill(square);
                        graph.draw(square);
                    }
                }
            }
        };

        resetPixels();
        panel.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
        add(panel);
    }

    public void runCalculation() {
        calculationService.submit(() -> {
            final int bound = RESOLUTION * RESOLUTION;
            final int middle = RESOLUTION / 2;

            int x = middle;
            int y = middle;
            int requiredLength = 1;
            int currentLength = 0;
            boolean extendRequiredLength = false;
            Direction direction = Direction.RIGHT;

            for (long value = 1; value <= bound; value++) {
                if (requiredLength == currentLength) {
                    direction = direction.next();
                    currentLength = 0;
                    if (extendRequiredLength) {
                        requiredLength += 1;
                    }
                    extendRequiredLength = !extendRequiredLength;
                }

                this.pixels[y][x] = Utilities.isPrime(value) ? PRIME_COLOR : NOT_PRIME_COLOR;
                panel.repaint();

                x += direction.x;
                y += direction.y;
                currentLength++;
            }
        });
    }

    private void resetPixels() {
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                this.pixels[y][x] = EMPTY_COLOR;
            }
        }
    }

}
