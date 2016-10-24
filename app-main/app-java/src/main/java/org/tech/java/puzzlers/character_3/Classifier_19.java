package org.tech.java.puzzlers.character_3;

public class Classifier_19 {
    public static void main(String[] args) {
        System.out.println(
            classify('n') + classify('+') + classify('2')); 
    }

    static String classify(char ch) {
        if ("0123456789".indexOf(ch) >= 0)
            return "NUMERAL ";
        if ("abcdefghijklmnopqrstuvwxyz".indexOf(ch) >= 0)
            return "LETTER ";
///*
// *      (Operators not supported yet) 
// *      if ("+-*/&|!=".indexOf(ch) >= 0)
// *          return "OPERATOR "; 
// */
        return "UNKNOWN "; 
    } 
}
