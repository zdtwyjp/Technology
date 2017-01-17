package org.tech.java.puzzlers.puzzlers_8_classier.puzzle_69;

public class FadeToBlack {
    // This method should read and print the value of the field Z in class X.Y
    public static void main(String[] args){
    	System.out.println(((X.Y)null).Z);
    }
}

class X {
    static class Y {
        static String Z = "Black";
    } 

    static C Y = new C();
}

class C {
    String Z = "White";
}
