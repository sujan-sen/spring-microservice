package com.springms.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	private String topic;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void sendMessage(String message) {
        if(topic==null || topic.isEmpty()) {
        	System.err.println("There is no Topic Present, hence cannot push message");
        }else {
        	System.out.println("Kafka message >>> "+message + " posted on topic >>> "+topic);
        	this.kafkaTemplate.send(topic, message);
        }
        
    }

}
