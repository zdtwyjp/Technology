package org.tech.kafka.demo1;

import org.tech.kafka.KafkaProperties;


public class ProducerTest {
    public static void main(String[] args) {
        Producer pt = new Producer(KafkaProperties.TOPIC, 2);
        pt.start();
    }
}
