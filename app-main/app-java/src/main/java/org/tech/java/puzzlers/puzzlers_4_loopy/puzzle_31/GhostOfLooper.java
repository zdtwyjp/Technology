package org.tech.java.puzzlers.puzzlers_4_loopy.puzzle_31;

public class GhostOfLooper {
    public static void main(String[] args) {
        // Place your declaration for i here
    	System.out.println(-1 >>> 1);
    	System.out.println((short)Integer.MAX_VALUE);
    	short i = -1;
        while (i != 0)
            i >>>= 1;
    	
    }
}
