package org.tech.java.puzzlers.puzzlers_8_classier.puzzle_70.hack;
import org.tech.java.puzzlers.puzzlers_8_classier.puzzle_70.click.CodeTalk;

public class TypeIt {
    private static class ClickIt extends CodeTalk {
    	void printMessage() {
            System.out.println("Hack");
        }
    }

    public static void main(String[] args) {
        ClickIt clickit = new ClickIt();
        clickit.doIt();
    }
}
