package com.example.shop.repository;

import com.example.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Date-5/9/2023
 * Time-6:36 AM
 */

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
