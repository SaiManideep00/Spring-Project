package com.example.demo.representingInheritance.mappedSuperClass;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
