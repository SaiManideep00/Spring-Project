package com.example.demo.representingInheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String specialization;
}
