package com.example.storeapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String email;
}
