package org.tech.java.puzzlers.puzzlers_3_character.puzzle_21;
import java.io.File;

public class MeToo {
    public static void main(String[] args) {
    System.out.println(MeToo.class.getName().
        replaceAll("\\.", File.separator) + ".class");
  }
}
