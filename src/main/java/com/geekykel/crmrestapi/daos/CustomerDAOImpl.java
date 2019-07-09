package com.geekykel.crmrestapi.daos;

import com.geekykel.crmrestapi.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        final String GET_ALL_CUSTOMER_QUERY = "from Customer";

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = currentSession.createQuery(GET_ALL_CUSTOMER_QUERY, Customer.class);

        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(customer);

    }

    @Override
    public Customer getCustomerById(int customerId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Customer customer = currentSession.get(Customer.class, customerId);

        return customer;

    }

    @Override
    public void deleteCustomerById(int customerId) {

        final String DELETE_CUSTOMER_QUERY = "delete from Customer where id=:customerId";

        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery(DELETE_CUSTOMER_QUERY);

        query.setParameter("customerId", customerId);

        query.executeUpdate();

    }
}
