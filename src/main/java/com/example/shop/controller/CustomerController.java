package com.example.shop.controller;

import com.example.shop.model.Customer;
import com.example.shop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Date-5/10/2023
 * Time-8:21 AM
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "{customerId}")
    public Customer getSupplier(@PathVariable("customerId") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public ResponseEntity<Customer> registerNewSupplier(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
        customerService.editCustomer(id, customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteSupplier(@PathVariable("customerId") Long id) {
        customerService.deleteCustomer(id);
    }
}
