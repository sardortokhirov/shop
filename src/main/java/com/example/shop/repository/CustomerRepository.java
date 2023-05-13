package com.example.shop.repository;

import com.example.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Date-5/9/2023
 * Time-6:23 AM
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
