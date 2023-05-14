package com.example.shop.controller;

import com.example.shop.model.Supplier;
import com.example.shop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Date-5/9/2023
 * Time-7:39 AM
 */
@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(path = "{supplierId}")
    public Supplier getSupplier(@PathVariable("supplierId") Long id) {
        return supplierService.getSupplier(id);
    }

    @PostMapping
    public ResponseEntity<Supplier> registerNewSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.addSupplier(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{supplierId}")
                .buildAndExpand(savedSupplier.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "{supplierId}")
    public void updateSupplier(@PathVariable("supplierId") Long id, @RequestBody Supplier supplier) {
        supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") Long id) {
        supplierService.deleteSupplier(id);
    }
}
