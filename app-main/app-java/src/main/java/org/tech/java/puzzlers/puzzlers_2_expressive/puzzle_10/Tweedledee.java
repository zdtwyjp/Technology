package org.tech.java.puzzlers.puzzlers_2_expressive.puzzle_10;

public class Tweedledee {
    public static void main(String[] args) {
        // Put your declarations for x and i here
    	Object x = "";
    	String i = "";
        x = x + i;  // Must be LEGAL
        x += i;     // Must be ILLEGAL
    }
}
