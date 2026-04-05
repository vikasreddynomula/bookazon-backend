package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_id;
	private String product_name;
	private int price;
	private String category;
	private String description;
	private String nameOfSeller;
	private String mobileNumber;
	private String author;
	private byte[] picture;
	private String isPlaced;

	
}
