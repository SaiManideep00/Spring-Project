package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashPassword;
    private String isEmailVerified;
    @ManyToMany
    private List<Role> roles;
}
