package com.ikinsure;

import static com.ikinsure.Spiral.*;

public class Utilities {

     static boolean isPrime(long value) {
         long halfValue = value / 2;
         if (value < 2) {
             return false;
         }
         for (long i = 2; i <= halfValue; i++) {
             if (value % i == 0) {
                 return false;
             }
         }
         return true;
    }

    static void spiralIterator(SpiralConsumer consumer) {
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

            consumer.accept(x, y, value);

            x += direction.x;
            y += direction.y;
            currentLength++;
        }
    }

    static void iterate2D(PositionConsumer consumer, int length) {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                consumer.accept(x, y);
            }
        }
    }

}
