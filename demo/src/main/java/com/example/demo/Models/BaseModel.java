package com.example.demo.Models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
