package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Services.ProductService;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;


import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService)
    {
        this.productService=productService;
    }
    @GetMapping()
    public List<Product> getAllProducts()
    {
        return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product=productService.getProductById(id);
        ResponseEntity<Product> response;
            response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;

    }

    @PutMapping("/{id}")
    public Product replaceProductById(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return productService.replaceProductById(id,product);
    }
}
