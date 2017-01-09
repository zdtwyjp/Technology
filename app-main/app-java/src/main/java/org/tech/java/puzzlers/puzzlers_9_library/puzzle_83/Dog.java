package org.tech.java.puzzlers.puzzlers_9_library.puzzle_83;

public class Dog extends Exception {
    public static final Dog INSTANCE = new Dog();
    private Dog() { }

    public String toString() {
        return "Woof";
    }
}
