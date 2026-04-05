package com.example.demo.filter;

import com.example.demo.entity.Product;
import com.example.demo.repos.ProductRepo;

import java.util.List;

public interface FilterStrategy {
    List<Product> filter(ProductRepo repo, String filter);
}
