package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repos.CartRepo;


@Service
public class CartService {
	

	private final CartRepo cartRepo;
	
	public CartService(CartRepo cartRepo) {
		this.cartRepo=cartRepo;
	}

	public void addToCart(Cart cart) {
		cartRepo.save(cart);
	}
	public void deleteFromCart(int id, String seller) {
		
		cartRepo.deleteById(cartRepo.findCartid(id,seller));
		
	}

	 public List<Integer> myCartItems(String sellerNumber){

		 return cartRepo.getallbysellerno(sellerNumber);
	 }

	
}
