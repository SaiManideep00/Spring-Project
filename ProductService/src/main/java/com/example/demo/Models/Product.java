package com.example.demo.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private Double price;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;
}
