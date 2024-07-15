package com.example.demo.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "msc_mentor")
public class Mentor extends User{
    private double mentorRating;

}
