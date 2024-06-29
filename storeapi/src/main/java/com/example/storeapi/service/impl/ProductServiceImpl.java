package com.example.storeapi.service.impl;

import com.example.storeapi.exception.ProductNotFoundException;
import com.example.storeapi.model.Product;
import com.example.storeapi.repository.ProductRepository;
import com.example.storeapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product updateProductById(Product updateProduct, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updateProduct.getName());
                    product.setPrice(updateProduct.getPrice());
                    product.setDescription(updateProduct.getDescription());
                    return productRepository.save(product);
                }) .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
