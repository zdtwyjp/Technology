package org.tech.kafka.demo1;




public class ConsumerTest {
    public static void main(String[] args) {
        Consumer ct = new Consumer("group1");
        ct.start();
        
        Consumer ct2 = new Consumer("group2");
        ct2.start();        
    }
}
