package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Orders_products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int orders_order_id;
	private int products_product_id;
	
}
