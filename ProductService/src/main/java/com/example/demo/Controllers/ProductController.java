package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Services.ProductService;
import com.example.demo.commons.AuthCommons;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.UserDto;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;
    ProductController(@Qualifier("selfServiceProduct") ProductService productService, AuthCommons authCommons)
    {
        this.productService=productService;
        this.authCommons=authCommons;
    }
    @GetMapping()
    public List<ProductDTO> getAllProducts()
    {
             return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id,@RequestHeader("token") String tokenValue) throws ProductNotFoundException {
        UserDto userDto=authCommons.validateToken(tokenValue);
        if(userDto!=null)
            return productService.getProductById(id);
        return null;

    }

    @PutMapping("/{id}")
    public ProductDTO replaceProductById(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id,product);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody Map<String,Object> values) throws ProductNotFoundException {
        return productService.updateProduct(id,values);
    }
}
