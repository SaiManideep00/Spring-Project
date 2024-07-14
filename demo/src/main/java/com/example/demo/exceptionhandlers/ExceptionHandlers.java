package com.example.demo.exceptionhandlers;

import com.example.demo.dtos.ExceptionDto;
import com.example.demo.dtos.ProductNotFoundDto;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException)
    {
        
        ProductNotFoundDto productNotFoundDto=new ProductNotFoundDto();
        productNotFoundDto.setId(productNotFoundException.getId());
        productNotFoundDto.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(productNotFoundDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException()
    {
        ExceptionDto exceptionDto=new ExceptionDto("There is some problem ","There is no resolution");
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
