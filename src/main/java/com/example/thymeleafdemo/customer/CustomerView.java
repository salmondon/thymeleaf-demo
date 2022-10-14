package com.example.thymeleafdemo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CustomerView {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        IdBean idBean = new IdBean();
        model.addAttribute("idBean", idBean);
        model.addAttribute("customerList", customerRepository.findAll());
        model.addAttribute("idBean", idBean);
        return "index";
    }

    // form to add customer page
    @GetMapping("/addCustomer")
    public String createCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/";
    }

    @GetMapping("/getCustomerFromId")
    public String getCustomerFromId(Model model, @ModelAttribute(value = "customerId")IdBean customerId) {
        Optional<Customer> customer = customerRepository.findById(Long.parseLong(customerId.getId()));
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "display_customer";
        } else {
            return "/redirect:/";
        }
    }
}
