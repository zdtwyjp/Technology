package org.tech.java.puzzlers.puzzlers_3_character.puzzle_20;

import java.util.regex.Pattern;

public class Me {
   public static void main(String[] args) {
      System.out.println(Me.class.getName().replaceAll(".", "/") + ".class");
      System.out.println(Me.class.getName().replaceAll("\\.", "/") + ".class");
      System.out.println(Pattern.quote("."));
      System.out.println(Me.class.getName().replaceAll(Pattern.quote("."), "/") + ".class");
      int i = Me.class.getName().indexOf(".");
      System.out.println(i);
   }
}
