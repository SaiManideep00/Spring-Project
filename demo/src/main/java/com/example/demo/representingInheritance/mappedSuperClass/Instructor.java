package com.example.demo.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;


@Entity(name = "msc_instructor")
public class Instructor extends User {
    private String specialization;
}
