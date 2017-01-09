package org.tech.java.puzzlers.puzzlers_2_expressive.puzzle_8;

public class DosEquis {
    public static void main(String[] args) {
        char x = 'X';
        int i = 0;
        System.out.println(true  ? x : 0); // 返回值类型：char
        System.out.println(false ? i : x); // 返回值类型：int
    }
}
