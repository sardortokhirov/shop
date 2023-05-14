package com.example.shop.service;

import com.example.shop.model.Customer;
import com.example.shop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date-5/9/2023
 * Time-6:39 AM
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public void editCustomer(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            customerRepository.save(customer);
        } else {
            System.err.println("User with id:" + id + " doesn't exist");
        }
    }

    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
