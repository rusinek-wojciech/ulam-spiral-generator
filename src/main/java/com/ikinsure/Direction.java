package com.ikinsure;

public enum Direction {

    RIGHT(1, 0),
    UP(0, -1),
    LEFT(-1, 0),
    DOWN(0, 1);

    final int x;
    final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction next() {
        if (RIGHT.equals(this)) {
            return UP;
        }
        if (UP.equals(this)) {
            return LEFT;
        }
        if (LEFT.equals(this)) {
            return DOWN;
        }
        if (DOWN.equals(this)) {
            return RIGHT;
        }
        throw new Error("Unexpected state");
    }
}
