package com.ikinsure;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ikinsure.Spiral.*;

public class SpiralFrame extends JFrame {

    private final Square[][] squares;
    private final JPanel panel;
    private final ExecutorService calculationService;

    public SpiralFrame() {
        this.squares =  new Square[RESOLUTION][RESOLUTION];
        this.calculationService = Executors.newSingleThreadExecutor();
        this.panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                final Graphics2D graph = (Graphics2D) g;
                Utilities.iterate2D((x, y) -> {
                    Square square = squares[y][x];
                    graph.setColor(square.getColor());
                    graph.fill(square.getRectangle());
                    graph.draw(square.getRectangle());
                }, squares.length);
            }
        };

        Utilities.iterate2D((x, y) -> squares[y][x] = new Square(x, y), squares.length);
        panel.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
        add(panel);
    }

    public void runSpiralCalculation() {
        calculationService.submit(() -> Utilities.spiralIterator((x, y, value) -> {
            Color color = Utilities.isPrime(value) ? PRIME_COLOR : NOT_PRIME_COLOR;
            squares[y][x].setColor(color);
            panel.repaint();
        }));
    }

}
