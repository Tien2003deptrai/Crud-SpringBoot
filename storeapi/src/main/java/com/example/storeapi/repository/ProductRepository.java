package com.example.storeapi.repository;

import com.example.storeapi.entity.ProductEntity;
import com.example.storeapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
