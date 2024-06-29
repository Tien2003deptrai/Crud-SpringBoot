package com.example.storeapi.controller;


import com.example.storeapi.model.Product;
import com.example.storeapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/products")
@RestController
@CrossOrigin("http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/")
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product updateProduct,@PathVariable Long id) {
        return productService.updateProductById(updateProduct, id);
    }

    @DeleteMapping("/{id}")
    String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Product deleted";
    }

}
