package com.example.storeapi.service;

import com.example.storeapi.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProductById(Product product, Long id);
    void deleteProductById(Long id);
}
