package com.example.shop.service;

import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * Date-5/9/2023
 * Time-6:39 AM
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Supplier supplier = this::getAllProducts;

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new ProductNotFoundException("We couldn't find product with id: " + id);
        });
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public void updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        } else {
            System.err.println("Product with id:" + id + " doesn't exist");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
