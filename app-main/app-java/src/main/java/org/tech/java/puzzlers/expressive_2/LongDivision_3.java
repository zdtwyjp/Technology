package org.tech.java.puzzlers.expressive_2;

public class LongDivision_3 {
    public static void main(String[] args) {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }
}
