package com.ikinsure;

@FunctionalInterface
public interface SpiralConsumer {
    void accept(int x, int y, long value);
}
