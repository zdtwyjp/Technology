package org.tech.java.puzzlers.puzzlers_2_expressive.puzzle_9;

public class Tweedledum {
    public static void main(String[] args) {
        // Put your declarations for x and i here
    	int i = 123456;
    	short x = 0;
        x += i;     // Must be LEGAL
//        x = x + i;  // Must be ILLEGAL
        System.out.println(x);
    }
}
