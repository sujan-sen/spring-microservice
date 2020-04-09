package com.springms.orderservice.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springms.orderservice.entities.Order;
import com.springms.orderservice.kafka.KafkaProducer;
import com.springms.orderservice.repositories.OrderRepository;

@Component
public class OrderHelper {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Value("${spring.kafka.producer.topic}")
	private String kafkaMqTopic;
	
	@Autowired
	private OrderRepository orderRepository;

	public String placeOrder(Order placedOrder) {
		String response = "";
		
		placedOrder.setOrderStatus("Pending");
		placedOrder.setMessage("Checking credit status");
		
		orderRepository.save(placedOrder);
		System.out.println("Order record partially saved...");
		
		System.out.println("Pushing data ");
		kafkaProducer.setTopic(kafkaMqTopic);
		kafkaProducer.sendMessage(
				placedOrder.getOrderNo() + ":" + placedOrder.getUserId() + ":" + placedOrder.getTotatOderAmount());
		response = "Order placed, checking against your credit amount. We will complete once done";
		return response;
	}
}

