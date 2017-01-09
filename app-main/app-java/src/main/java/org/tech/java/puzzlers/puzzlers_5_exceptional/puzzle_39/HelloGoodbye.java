package org.tech.java.puzzlers.puzzlers_5_exceptional.puzzle_39;

public class HelloGoodbye {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world");
            System.exit(0);
        } finally {
            System.out.println("Goodbye world");
        }
    } 
}
