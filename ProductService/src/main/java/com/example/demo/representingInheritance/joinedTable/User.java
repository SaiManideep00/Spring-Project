package com.example.demo.representingInheritance.joinedTable;

import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="jt_user")

public  class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
