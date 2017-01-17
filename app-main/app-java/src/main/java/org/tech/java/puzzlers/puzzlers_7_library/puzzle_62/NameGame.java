package org.tech.java.puzzlers.puzzlers_7_library.puzzle_62;

import java.util.*;

public class NameGame {
    public static void main(String args[]) {
        Map<String, String> m = new IdentityHashMap<String, String>();
//        m.put("Mickey", "Mouse");
//        m.put("Mickey", "Mantle");
        m.put(new String("dd"), "first");
        m.put("dd", "first");
        System.out.println(m.size());
        System.out.println("--------------------");
        Map<String, String> map = new HashMap<String, String>();
        map.put(new String("dd"), "first");
        map.put("dd", "first");
        System.out.println(map.size());
    } 
}
