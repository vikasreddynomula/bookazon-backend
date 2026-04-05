package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.OrdersService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrdersController {
	
	private final OrdersService ordersService;

	
	public OrdersController(OrdersService ordersService) {
		this.ordersService=ordersService;

	}
	

	@PostMapping("/placeorder")
	public String placeorder(@RequestParam String delivery_address,@RequestParam String customer_contact,@RequestParam int order_amount,@RequestParam List<Integer> products) throws Exception {

		ordersService.placeOrder(delivery_address, customer_contact, order_amount,products);
	
		return "placedSuccessFully";

	}

	@GetMapping("/myorders")
	public List<Product> myoders(@RequestParam String customer_contact){
		return ordersService.getAllOrders(customer_contact);
	}

	@PostMapping("/cancelorder")
	public List<Product> cancelorder(@RequestParam int id,@RequestParam String mobile_number) throws Exception {
	
	 	ordersService.cancelOrder(id,mobile_number);
	 return ordersService.getAllOrders(mobile_number);
	}

}

