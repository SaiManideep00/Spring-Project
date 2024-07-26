package com.example.demo.Services;

import com.example.demo.Models.Product;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ProductService {
    ProductDTO getProductById(Long id) throws ProductNotFoundException;
    List<ProductDTO> getAllProducts();

    ProductDTO replaceProduct(Long id, Product product) throws ProductNotFoundException;

    ProductDTO updateProduct(Long id, Map<String,Object> values) throws ProductNotFoundException;
    Product createProduct(Product product);
    void deleteProduct(Long id) throws ProductNotFoundException;
}
