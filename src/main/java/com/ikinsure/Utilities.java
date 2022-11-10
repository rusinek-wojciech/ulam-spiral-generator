package com.ikinsure;

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

}
