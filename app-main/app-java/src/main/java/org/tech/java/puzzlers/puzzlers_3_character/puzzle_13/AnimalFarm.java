package org.tech.java.puzzlers.puzzlers_3_character.puzzle_13;

public class AnimalFarm {
    public static void main(String[] args) {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();

        System.out.println("Animals are equal: "
                           + pig == dog);
    }
}
