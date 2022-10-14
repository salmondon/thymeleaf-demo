package com.example.thymeleafdemo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/all")
    public Iterable<Customer> getCustomerList() {
        return customerRepository.findAll();
    }
}
