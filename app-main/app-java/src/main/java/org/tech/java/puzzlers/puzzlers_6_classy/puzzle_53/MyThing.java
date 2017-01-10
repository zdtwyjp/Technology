package org.tech.java.puzzlers.puzzlers_6_classy.puzzle_53;

public class MyThing extends Thing 
{
    private final int arg;

    /*
     * This constructor is illegal. Rewrite it so that it has the same
     * effect but is legal.
     */
    public MyThing() {
        this((int)System.currentTimeMillis());
    }
    
    public MyThing(int i) {
    	super(i);
    	arg = i;
    }
}
