package org.tech.java.puzzlers.puzzlers_8_classier.puzzle_71;

import static java.util.Arrays.toString;

class ImportDuty {
    public static void main(String[] args) {
        printArgs(1, 2, 3, 4, 5);
    }

    static void printArgs(Object... args) {
        System.out.println(toString(args));
    }
}