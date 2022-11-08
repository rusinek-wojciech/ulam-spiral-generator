package com.ikinsure;

public class Utilities {

     static boolean isPrime(long value) {
        for (long i = 2; i < value / 2; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
