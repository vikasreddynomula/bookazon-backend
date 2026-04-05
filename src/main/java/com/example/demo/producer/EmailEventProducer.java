package com.example.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmailEvent;

@Service
public class EmailEventProducer {
	
	private static final String TOPIC="document-generated-topic";
	
	private final KafkaTemplate<Integer, EmailEvent> kafkaTemplate;

    public EmailEventProducer(KafkaTemplate<Integer, EmailEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmailEvent(String email) {
    	EmailEvent emailEvent=new EmailEvent();
    	emailEvent.setEventType("PASSWORD_RESET");
    	emailEvent.setRecipientEmail(email);
        kafkaTemplate.send(TOPIC, 1, emailEvent);
        System.out.println("✅ email Event Sent: " + email);
    }

}
