package org.tech.java.puzzlers.puzzlers_3_character.puzzle_21;
import java.io.File;
import java.util.regex.Matcher;

public class MeToo {
    public static void main(String[] args) {
		System.out.println(File.separator);
		System.out.println(Matcher.quoteReplacement(File.separator));
		System.out.println(MeToo.class.getName().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");
		System.out.println(MeToo.class.getName().replaceAll("\\.", File.separator) + ".class");
    
  }
}
