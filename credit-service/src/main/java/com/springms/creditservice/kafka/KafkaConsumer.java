package com.springms.creditservice.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springms.creditservice.entities.Credit;
import com.springms.creditservice.repositories.CreditRepository;

@Service
@Transactional
public class KafkaConsumer {

	@Autowired
	private CreditRepository creditRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Value("${spring.kafka.producer.topic}")
	private String topic;

	@KafkaListener(topics = "order-service-event", groupId = "credit-grp")
	public void consume(String message) throws IOException {
		System.out.println("Received Message >>>" + message);
		String kafkaResp = null;

		String[] msg = message.split(":");
		double totalOrderAmount = Double.parseDouble(msg[2]);
		int orderId = Integer.parseInt(msg[0]);

		try {
			Credit credit = creditRepository.getOne(Integer.parseInt(msg[1]));

			if ((credit.getMaxCreditAmount() - credit.getCreditUsed()) >= totalOrderAmount) {
				System.out.println("Order amout is within credit limit, hence approving");
				credit.setCreditUsed(credit.getCreditUsed() + totalOrderAmount);
				creditRepository.save(credit);

				System.out.println(
						"Amount deducted for User Id " + orderId + " for user >>> " + Integer.parseInt(msg[1]));
				kafkaResp = orderId + ":" + true;
			} else {
				kafkaResp = orderId + ":" + false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			kafkaResp = orderId + ":" + false;
		}
		kafkaProducer.setTopic(topic);
		kafkaProducer.sendMessage(kafkaResp);

	}
}
