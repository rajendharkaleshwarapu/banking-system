package com.bankingsystem.service;

import com.bankingsystem.exception.NotFoundException;
import com.bankingsystem.exception.ValidationException;
import com.bankingsystem.model.Customer;
import com.bankingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String name, String email, String phone) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email already exists");
        }
        Customer customer = new Customer(name, email, phone);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, String name, String email, String phone) {
        Customer customer = getCustomer(id);
        if (!customer.getEmail().equals(email) && customerRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email already exists");
        }
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
