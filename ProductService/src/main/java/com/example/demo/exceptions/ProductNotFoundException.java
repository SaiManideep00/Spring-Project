package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductNotFoundException extends Exception{
    private Long id;
    private String message;
    public ProductNotFoundException(Long id,String message)
    {
        this.message=message;
        this.id=id;
    }
}
