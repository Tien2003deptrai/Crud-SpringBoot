package com.example.storeapi.service.impl;

import com.example.storeapi.entity.ProductEntity;
import com.example.storeapi.exception.ProductNotFoundException;
import com.example.storeapi.model.Product;
import com.example.storeapi.repository.ProductRepository;
import com.example.storeapi.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        productRepository.save(productEntity);
        product.setId(productEntity.getId());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public Product updateProductById(Product updateProduct, Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        BeanUtils.copyProperties(updateProduct, productEntity, "id");
        productRepository.save(productEntity);
        return updateProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
