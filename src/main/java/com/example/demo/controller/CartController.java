package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	private final CartService cartService;
	private final ProductService productService;
	
	public CartController(CartService cartService,ProductService productService) {
		this.cartService=cartService;
		this.productService=productService;
	}
	
	@PostMapping("/addcart")
	public void addtocart(Cart cart) {
		cartService.addToCart(cart);
	}
	
	@GetMapping("/viewcart")
	public List<Product> viewAll(@RequestParam String mobileNumber){
		List<Integer>ids=cartService.myCartItems(mobileNumber);
		List<Product> products= ids.stream()
				.map(productService::getById)
				.collect(Collectors.toList());

		return products;
	}
	@DeleteMapping("/delcart/{id}")
	public List<Product> deleteFromCart(@PathVariable int id,@RequestParam String mobileNumber) {
		cartService.deleteFromCart(id,mobileNumber);
		return viewAll(mobileNumber);
	}
	@GetMapping("/getcartcount")
	public int getcount( String seller) {
		
		List<Integer> li=cartService.myCartItems(seller);
		 return li.size();
	}
	@PostMapping("/getproductids")
	public List<Integer> productids( String mobile_number){
		return cartService.myCartItems(mobile_number);
	}

	
}
