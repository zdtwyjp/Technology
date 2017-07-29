package org.tech.kafka.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer extends Thread {
	private static Logger logger = LoggerFactory.getLogger(Consumer.class);
	private String CONFIGPATH = "kafkaConsumer.properties";
	public final String TOPIC_TIS = "tis";
	private Properties props = new Properties();
	private KafkaConsumer<String, String> consumer;

	private String groupId;

	public Consumer(String groupId) {
		this.groupId = groupId;
		init();
	}

	private void init() {
		InputStream is = null;
		try {
			is = Consumer.class.getResourceAsStream(CONFIGPATH);
			props.load(is);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
			consumer = new KafkaConsumer<String, String>(props);
			consumer.subscribe(Collections.singletonList(TOPIC_TIS));
		} catch (Exception e) {
			logger.error("∂¡»°≈‰÷√Œƒº˛[kafkaProduce.properties] ß∞‹°£", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public void run() {
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("Consumer >> " + this.groupId
						+ " >> Received message: (" + record.topic() + ", "
						+ record.value() + ") at offset " + record.offset());
			}
		}
	}
}
