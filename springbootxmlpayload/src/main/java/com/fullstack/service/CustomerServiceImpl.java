package com.fullstack.service;

import com.fullstack.entity.Customer;
import com.fullstack.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer signUp(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(long custId) {
        return customerRepository.findById(custId);
    }

    public boolean signIn(String custEmailId, String custPassword) {

        boolean flag = false;

        Customer customer = customerRepository.findByCustEmailIdAndCustPassword(custEmailId, custPassword);

        if (customer != null) {
            flag = true;

        }

        return flag;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteById(long custId) {
        customerRepository.deleteById(custId);
    }
}
