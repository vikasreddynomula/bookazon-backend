package com.example.demo.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_id;
	private int order_amount;
	private String delivery_address;
	private String customer_contact;
	
	@ManyToMany
	List<Product> products;
	
}
