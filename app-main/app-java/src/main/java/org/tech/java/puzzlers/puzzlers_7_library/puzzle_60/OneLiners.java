package org.tech.java.puzzlers.puzzlers_7_library.puzzle_60;

import java.util.*;

public class OneLiners {
    public static void main(String[] args) {
        // Part A
        String[] breakfast = { "spam", "sausage", "spam", "spam", "bacon",
                               "spam", "tomato", "spam" };
        System.out.println(withoutDuplicates(Arrays.asList(breakfast)));

        // Part B
        String weaponry = "fear, surprise,ruthless efficiency, an almost " +
            "fanatical devotion to the Pope, nice red uniforms";
        System.out.println(Arrays.asList(parse(weaponry)));

        // Part C
        int[][] magic = {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}};
        System.out.println(arrayToString(magic));

        // Part D
        System.out.println(hasMoreBitsSet(0x0f0f0f0f, 0x88888888)); // true
        System.out.println(hasMoreBitsSet(0x88888888, 0x0f0f0f0f)); // false
    }

    // Part A
    static <E> List<E> withoutDuplicates(List<E> original) {
        // Your code goes here
    	return new ArrayList<E>(new LinkedHashSet<E>(original));
    }

    // Part B
    static String[] parse(String string) {
        // Your code goes here
    	return string.split(".\\S*");
    }

    // Part C
    static String arrayToString(Object[] array) {
        // Your code goes here
    	return Arrays.deepToString(array);
    }

    // Part D
    static boolean hasMoreBitsSet(int i, int j) {
        // Your code goes here
    	return Integer.bitCount(i) > Integer.bitCount(j);
    }
}
