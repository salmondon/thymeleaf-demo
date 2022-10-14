package com.example.thymeleafdemo.invoice;

import com.example.thymeleafdemo.customer.Customer;
import com.example.thymeleafdemo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping(path = "/api/invoice/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable(value = "id") long id) {
        Optional<Invoice> response = invoiceRepository.findById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/api/invoice/all")
    public Iterable<Invoice> GetInvoiceList() {
        return invoiceRepository.findAll();
    }

    @PostMapping(path = "/api/customer/{id}/invoice/add")
    public ResponseEntity<Invoice> addNewInvoice(@PathVariable(value = "id") long id, @RequestBody Invoice invoiceRequest) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            invoiceRequest.setCustomer(customer.get());
            invoiceRequest.setDate(LocalDate.now().toString());
            invoiceRepository.save(invoiceRequest);
            return new ResponseEntity<>(invoiceRequest, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/api/invoice/{id}/delete")
    public ResponseEntity<HttpStatus> deleteInvoiceById(@PathVariable("id") long id) {
        invoiceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}