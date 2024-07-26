package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value;
    @ManyToOne
    private User user;
    private LocalDate expiryAt;
}
