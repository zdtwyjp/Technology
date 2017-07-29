package org.tech.kafka.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer extends Thread {
	private Logger logger = LoggerFactory.getLogger(Producer.class);
	private String CONFIGPATH = "kafkaProduce.properties";
	private Properties props = new Properties();
	private KafkaProducer<String, String> producer;
	
	private int len = 10;
	private String topic;
	
	public Producer(String topic, int len) {
		this.len = len;
		this.topic = topic;
		InputStream is = null;
		try {
			is = Producer.class.getResourceAsStream(CONFIGPATH);
			props.load(is);
			producer = new KafkaProducer<String, String>(props);
		} catch (Exception e) {
			logger.error("��ȡ�����ļ�[kafkaProduce.properties]ʧ�ܡ�", e);
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

	/**
	 * ��Kafka������Ϣ
	 * 
	 * @author:junping.yang
	 * @param value
	 *            void
	 * @throws
	 */
	private void produceMsgToKafka(String value) {
		try {
			producer.send(new ProducerRecord<String, String>(topic, value))
					.get();
			logger.info("Sent message topic: (" + topic + " : " + value + ")");
		} catch (Exception e) {
			logger.error("�������ݵ�kafkaʧ�ܣ�", e);
		}
	}

	@Override
	public void run() {
		for (int i = 1; i <= len; i++) {
			produceMsgToKafka("msg_" + i);
		}
	}

}
