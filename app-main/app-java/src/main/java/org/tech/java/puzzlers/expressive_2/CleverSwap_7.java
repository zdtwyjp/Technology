package org.tech.java.puzzlers.expressive_2;

public class CleverSwap_7 {
	public static void main(String[] args) {
		int x = 1984;
		int y = 2001;
		x ^= y ^= x ^= y;
		System.out.println("x = " + x + "; y = " + y);
	}
}
