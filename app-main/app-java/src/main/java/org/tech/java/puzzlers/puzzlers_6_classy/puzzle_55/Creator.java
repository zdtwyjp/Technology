package org.tech.java.puzzlers.puzzlers_6_classy.puzzle_55;

public class Creator {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
        	new Creature();
        }
        System.out.println(Creature.numCreated());
    }
}

class Creature {
    private static long numCreated = 0;

    public Creature() {
        numCreated++;
    }

    public static long numCreated() {
        return numCreated;
    }
}
