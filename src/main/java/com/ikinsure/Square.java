package com.ikinsure;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.ikinsure.Spiral.EMPTY_COLOR;
import static com.ikinsure.Spiral.PIXEL_SIZE;

public class Square {

    private final Rectangle2D.Float rectangle;
    private Color color;

    public Square(int x, int y) {
        this.rectangle = new Rectangle2D.Float(
                x * PIXEL_SIZE,
                y * PIXEL_SIZE,
                PIXEL_SIZE,
                PIXEL_SIZE
        );
        this.color = EMPTY_COLOR;
    }

    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
