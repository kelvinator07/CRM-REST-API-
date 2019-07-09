package com.geekykel.crmrestapi.Services;

import com.geekykel.crmrestapi.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    void deleteCustomerById(int customerId);
}
