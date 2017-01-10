package org.tech.java.puzzlers.puzzlers_4_loopy.puzzle_24;

class BigDelight {
    public static void main(String[] args) {
    	System.out.println(0x90);
    	System.out.println(Integer.toHexString(144));
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90) {
            	System.out.print("Joy!");
            }
        }
    }
}
