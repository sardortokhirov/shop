package com.example.shop.repository;

import com.example.shop.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Date-5/9/2023
 * Time-6:35 AM
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findByEmail(String email);
}
