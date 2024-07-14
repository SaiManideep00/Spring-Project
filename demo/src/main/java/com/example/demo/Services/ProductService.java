package com.example.demo.Services;

import com.example.demo.Models.Product;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product replaceProductById(Long id, Product product);
}
