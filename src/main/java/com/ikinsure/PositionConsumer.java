package com.ikinsure;

@FunctionalInterface
public interface PositionConsumer {
    void accept(int x, int y);
}
