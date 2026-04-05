package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@PostMapping(path="/Create")
	public String createProduct(Product product,MultipartFile image)throws IOException {
		
		byte[] imageData=image.getBytes();

		product.setPicture(imageData);
		productService.addProduct(product);
		
		return "added";
	}
	
	@GetMapping(path="/disp/{id}")
	public void viewProduct(@PathVariable int id,HttpServletResponse response)throws IOException,ServletException {
		Product product =productService.getById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.getPicture());
		response.getOutputStream().close();
	}
	
	@GetMapping(path="/getall")
	public List<Product> getAll(){
		return productService.fetchAll();
	}
	
	@PostMapping(path="/search")
	public List<Product> filterProducts(String filter,String search){
		return productService.filterProducts(filter,search);
	}
	
	@PostMapping(path="/sortby")
	public List<Product> sortBy(String sortParam){
		return productService.sortByPrice(sortParam);
	}
	
	@GetMapping("/mybooks")
	public List<Product> getProductsBySeller(@RequestParam String sellerContact){

		return productService.getProductsBySeller(sellerContact);
	}
	
	@DeleteMapping("/deleterr/{id}")
	public List<Product> getAll(@PathVariable int id,String sellerContact){

		return productService.deleteAndGetRest(id,sellerContact);
	}
	
	@GetMapping("/view/{id}")
	public List<Product> getById(@PathVariable int id) {
		
		List<Product> li= new ArrayList<>();
		li.add(productService.getById(id));
		return li;
		
	}
	
	

}
