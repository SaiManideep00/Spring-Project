package com.example.demo.dtos;

public record FakeStoreProduct(Long id,String title,Double price,String category, String description,String image) {
    public FakeStoreProduct() {
        this(null, null, null, null, null, null);
    }
}

