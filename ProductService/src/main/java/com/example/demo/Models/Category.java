package com.example.demo.Models;


import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;

}
