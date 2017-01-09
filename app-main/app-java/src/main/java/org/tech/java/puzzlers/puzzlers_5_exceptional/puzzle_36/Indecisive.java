package org.tech.java.puzzlers.puzzlers_5_exceptional.puzzle_36;

public class Indecisive { 
    public static void main(String[] args) {
        System.out.println(decision());
    }

    static boolean decision() {
        try {
            return true;
        } finally {
            return false;
        }
    } 
}
