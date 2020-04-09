package com.springms.orderservice.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springms.orderservice.entities.Order;
import com.springms.orderservice.repositories.OrderRepository;

@Service
@Transactional
public class KafkaConsumer {

	@Autowired
	private OrderRepository orderRepository;

	@KafkaListener(topics = "credit-service-event", groupId = "order-grp")
	public String consume(String message) throws IOException {
		System.out.println("Received Message >>>" + message);
		String[] msg = message.split(":");
		boolean isApproved = Boolean.parseBoolean(msg[1]);

		Order selectedOrder = orderRepository.getOne(Integer.parseInt(msg[0]));
		selectedOrder.setMessage(isApproved ? "Order placed successfully" : "Insufficient Credit Limit");
		selectedOrder.setOrderStatus(isApproved ? "Completed" : "Failed");
		orderRepository.save(selectedOrder);
		
		System.out.println("Order status updated successfully");

		return msg[1];
	}
}
