package com.geekykel.crmrestapi.controllers;

import com.geekykel.crmrestapi.HandleException.CustomerNotFoundException;
import com.geekykel.crmrestapi.Services.CustomerService;
import com.geekykel.crmrestapi.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> listCustomers() {

        List<Customer> customers = customerService.getCustomers();
        int i = 0;
        for (Customer customer: customers){
            i++;
            System.out.println("Customer: " + i + " " + customer);
        }

        return customers;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null)
            throw new CustomerNotFoundException("Customer With Id " + customerId + " Not Found");

        return customer;

    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {

        customer.setId(0);

        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null)
            throw new CustomerNotFoundException("Customer With Id " + customerId + " Not Found");

        customerService.deleteCustomerById(customerId);

        return "Deleted Customer Id >> " + customerId;
    }

}
