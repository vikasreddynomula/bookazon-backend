package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.filter.FilterStrategy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repos.ProductRepo;

@Service
public class ProductService {

	private final ProductRepo productRepo;

	private final Map<String, FilterStrategy> strategies;
	
	public ProductService(ProductRepo productRepo,Map<String, FilterStrategy> strategies) {
		this.productRepo=productRepo;
		this.strategies=strategies;
	}
	
	public void addProduct(Product product){
		product.setIsPlaced("false");
		productRepo.save(product);
	}


	public List<Product> fetchAll() {
		
		return productRepo.findAllByFlag();
	}

	public List<Product> filterProducts(String filter, String search) {
		FilterStrategy strategy=strategies.get(filter);
		if(strategy==null){
			throw new IllegalArgumentException("Invalid Filter");
		}
		return strategy.filter(productRepo,search);
	}

	public List<Product> sortByPrice(String sortParam) {
		if(sortParam.equals("ltoh")) {
		return productRepo.findAll(Sort.by("price"));
		}
		else {
			return productRepo.findAll(Sort.by("price").descending());
		}
	}
	public List<Product> getProductsBySeller(String seller){
		return productRepo.findBySellerMobileNumber(seller);
	}

	public List<Product> deleteAndGetRest(int id,String seller) {
		productRepo.deleteById(id);
		return productRepo.findBySellerMobileNumber(seller);
	}

	public Product getById(int id) {
		
		return productRepo.getByProduct_Id(id);
	}

	public List<Product> markAsPlaced(List<Integer> products){
		List<Product> items = productRepo.findByProduct_Id(products);

		for(Product p:items) {
			p.setIsPlaced("true");
			productRepo.save(p);
		}

		return items;
	}

	public Product markAsCancelled(int productId) {
		Product product = productRepo.getByProduct_Id(productId);
		product.setIsPlaced("false");
		return productRepo.save(product);
	}
	
}
