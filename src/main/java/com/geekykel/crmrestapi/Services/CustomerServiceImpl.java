package com.geekykel.crmrestapi.Services;

import com.geekykel.crmrestapi.daos.CustomerDAO;
import com.geekykel.crmrestapi.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Transactional
    @Override
    public List<Customer> getCustomers() {

        return customerDAO.getCustomers();
    }

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Transactional
    @Override
    public Customer getCustomerById(int customerId) {

        return customerDAO.getCustomerById(customerId);
    }

    @Transactional
    @Override
    public void deleteCustomerById(int customerId) {
        customerDAO.deleteCustomerById(customerId);
    }
}
