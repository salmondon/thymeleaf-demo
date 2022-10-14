package com.example.thymeleafdemo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/all")
    public Iterable<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<Customer> getById(@PathVariable(value = "id") long id) {
        Optional<Customer> response = customerRepository.findById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
