package com.example.demo.filter;

import com.example.demo.entity.Product;
import com.example.demo.repos.ProductRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("name")
public class NameFilter implements FilterStrategy{
    @Override
    public List<Product> filter(ProductRepo repo, String filter) {
        return repo.filterByName(filter);
    }
}
