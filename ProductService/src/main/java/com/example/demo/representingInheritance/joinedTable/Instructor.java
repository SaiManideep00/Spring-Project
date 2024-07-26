package com.example.demo.representingInheritance.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "user_id")
@Entity(name = "jt_instructor")
public class Instructor extends User {
    private String specialization;
}
