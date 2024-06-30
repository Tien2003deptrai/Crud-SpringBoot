package com.example.storeapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String description;
}
