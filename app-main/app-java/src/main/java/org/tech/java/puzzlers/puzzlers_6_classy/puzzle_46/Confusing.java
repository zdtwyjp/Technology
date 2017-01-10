package org.tech.java.puzzlers.puzzlers_6_classy.puzzle_46;

public class Confusing {
	private Confusing() {
		System.out.println("null");
	}
    private Confusing(Object o) {
        System.out.println("Object");
    }

    private Confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        new Confusing((Object)null);
        new Confusing(null);
    }
}
