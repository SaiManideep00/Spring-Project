package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Services.ProductService;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    @Qualifier("selfServiceProduct")
    private ProductService productService;

    @Test
    void validProductById()  throws ProductNotFoundException {
    ProductDTO product=new ProductDTO("MacBook","Laptop",150000.00,"image","Apple");
    when(productService.getProductById(1L)).thenReturn(product);
    ProductDTO actualProduct=productController.getProductById(1L,"token");
    assertEquals(product,actualProduct);
    }

    @Test
    void getAllProducts()
    {
        List<ProductDTO> productDTOList=new ArrayList<>();
        productDTOList.add(new ProductDTO("Google pixel 6a","android phone",300.00,"iamgeUrl","mobile"));
        productDTOList.add(new ProductDTO("Google pixel 7a","android phone",400.00,"iamgeUrl","mobile"));
        productDTOList.add(new ProductDTO("Google pixel 8a","android phone",500.00,"iamgeUrl","mobile"));
        productDTOList.add(new ProductDTO("Google pixel 9a","android phone",600.00,"iamgeUrl","mobile"));
        when(productService.getAllProducts()).thenReturn(productDTOList);
        assertIterableEquals(productDTOList,productController.getAllProducts());
    }
}