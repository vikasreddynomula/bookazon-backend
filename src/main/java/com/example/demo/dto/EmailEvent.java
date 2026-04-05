package com.example.demo.dto;
import lombok.Data;
@Data
public class EmailEvent {
	
	private String eventType;
	private String recipientEmail;
	private int order_amount;
	private String address;
	private int order_id;
	private String documentURI;

}
