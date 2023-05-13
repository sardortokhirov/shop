package com.example.shop.controller;

import com.example.shop.model.Customer;
import com.example.shop.model.Supplier;
import com.example.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void registerNewSupplier(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
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
