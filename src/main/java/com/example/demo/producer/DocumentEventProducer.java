package com.example.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;

@Service
public class DocumentEventProducer {
	private static final String TOPIC = "order-placed-topic";

    private final KafkaTemplate<Integer, Orders> kafkaTemplate;

    public DocumentEventProducer(KafkaTemplate<Integer, Orders> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderEvent(Orders event) {
        kafkaTemplate.send(TOPIC, event.getOrder_id(), event);
        System.out.println("✅ Order Event Sent: " + event.getOrder_id());
    }
}

