package com.example.demo.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private Double price;
    private String image;
    @ManyToOne
    private Category category;
}
