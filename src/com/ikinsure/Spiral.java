package com.ikinsure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Spiral extends JFrame {

    private static final int SIZE = 201;
    private static final int PIXEL_SIZE = 2;
    private static final Color PRIME_COLOR = Color.BLACK;
    private static final Color EMPTY_COLOR = Color.WHITE;

    enum Direction {
        RIGHT(1, 0),
        UP(0, -1),
        LEFT(-1, 0),
        DOWN(0, 1);
        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x * Spiral.PIXEL_SIZE;
            this.y = y * Spiral.PIXEL_SIZE;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            final long bound = SIZE * SIZE + 1;
            final int middle = Spiral.PIXEL_SIZE * (SIZE / 2);
            final Color[][] pixels = new Color[SIZE + 1][SIZE + 1];

            for (int i = 0; i <= SIZE; i++) {
                for (int j = 0; j <= SIZE; j++) {
                    pixels[i][j] = EMPTY_COLOR;
                }
            }

            JFrame frame = new Spiral();
            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    final Graphics2D graph = (Graphics2D) g;

                    int mainCounter = 1;
                    int minCounter = 0;
                    int stateCounter = 0;

                    boolean flag = true;
                    int actualX = middle;
                    int actualY = middle;
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
                        actualX += direction.x;
                        actualY += direction.y;

                        final int xIndex = actualX / PIXEL_SIZE;
                        final int yIndex = actualY / PIXEL_SIZE;

                        if (Utilities.isPrime(value)) {
                            pixels[xIndex][yIndex] = PRIME_COLOR;
                        }

                        var square = new Rectangle2D.Float(
                                actualX,
                                actualY,
                                Spiral.PIXEL_SIZE,
                                Spiral.PIXEL_SIZE
                        );
                        graph.setColor(pixels[xIndex][yIndex]);
                        graph.fill(square);
                        graph.draw(square);
                        repaint();
                    }
                }
            };
            frame.setSize(SIZE, SIZE);
            frame.setResizable(false);
            frame.setTitle("Ulam spiral");
            frame.setVisible(true);
            frame.setContentPane(panel);
        });
    }

}
