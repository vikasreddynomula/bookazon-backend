package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	@Query("from Product p where p.product_name like %:search% ")
	List<Product> filterByName(String search);

	@Query("from Product p where p.author like %:search% ")
	List<Product> filterByAuthor(String search);

	@Query("from Product p where p.category like %:search% ")
	List<Product> filterByCategory(String search);
	
	@Query("from Product p where p.mobileNumber=:seller and p.isPlaced='false'")
	List<Product> findBySellerMobileNumber(String seller);
	
	@Query("from Product p where p.product_id=:id")
	Product getByProduct_Id(int id);

	@Query("from Product p where p.product_id in (:products)")
	List<Product> findByProduct_Id(List<Integer> products);

	@Query("from Product p where p.isPlaced='false'")
	List<Product> findAllByFlag();

}
