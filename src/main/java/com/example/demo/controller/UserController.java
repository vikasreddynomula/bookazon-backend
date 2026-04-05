package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/get")
	public List<User> getAllUsers(){
		
		return userService.findAll();
	}
	
	@PostMapping("/verification")
	public String authenticate(@RequestParam String email,@RequestParam String password ) {
		
		if(userService.isAuthenticated(email, password)!=null) {

			return "pass"+userService.findByEmail(email);
		}
		else {
			
			return "fail";
		}
	}
	
	@PostMapping("/signup")
	public String signUp(User user) {
		
		userService.addUser(user);
		return "insert successfull";
	}
	
	@GetMapping("/details")
	public List<User> userdetails(@RequestParam String number) {
		
		return userService.findByNumber(number);
		
	}
	
	@PostMapping("/resetPassword")
	public void resetPassword(@RequestParam String email) {
		System.out.println(email);
		userService.resetPassword(email);
	}
	
	
	
}
