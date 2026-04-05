package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.producer.EmailEventProducer;
import com.example.demo.repos.UserRepo;

@Service
public class UserService {

	
	private final UserRepo userRepo;
	
	private final EmailEventProducer emailEventProducer;
	
	public UserService(UserRepo userRepo,EmailEventProducer emailEventProducer) {
		this.userRepo=userRepo;
		this.emailEventProducer=emailEventProducer;
	}
	 
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public User isAuthenticated(String username,String password) {
		return userRepo.findByUsernameAndPassword(username,password);
	}

	public void addUser(User user) {
		userRepo.save(user);
		
	}

	public String findByEmail(String email) {
		return userRepo.findByEmail(email);
		
	}

	public List<User> findByNumber(String number) {
		return userRepo.findByNumber(number);
		
	}

	public String findMailByNumber(String customer_contact) {
		
		return userRepo.findEmailByNumber(customer_contact);
	}

	public void resetPassword(String email) {
		emailEventProducer.publishEmailEvent(email);
		
	}



}
