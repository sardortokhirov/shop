package com.example.shop.service;

import com.example.shop.model.Supplier;
import com.example.shop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Date-5/9/2023
 * Time-6:39 AM
 */
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    public void editSupplier(Long id, Supplier supplier) {
        if (supplierRepository.existsById(id)) {
            supplier.setId(id);
            supplierRepository.save(supplier);
        }else {
            System.err.println("User with id:"+id+" doesn't exist");
        }
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
