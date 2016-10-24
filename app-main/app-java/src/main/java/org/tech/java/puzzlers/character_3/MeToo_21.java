package org.tech.java.puzzlers.character_3;
import java.io.File;

public class MeToo_21 {
    public static void main(String[] args) {
    System.out.println(MeToo_21.class.getName().
        replaceAll("\\.", File.separator) + ".class");
  }
}
