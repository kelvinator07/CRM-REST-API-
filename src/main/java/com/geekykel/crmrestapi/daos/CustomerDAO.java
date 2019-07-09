package com.geekykel.crmrestapi.daos;

import com.geekykel.crmrestapi.entities.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    void deleteCustomerById(int customerId);
}
