package com.ecommerce.ecommercejpa.customer;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public CustomerModel getCustomerByCpf(String cpf){
        Optional<CustomerModel> c = repository.findByCpf(cpf);
        if(c.isPresent()){
            return c.get();
        }
        throw new NoSuchElementException("Customer not found");
    }

    public CustomerModel createCustomer(CustomerModel customer){
        Optional<CustomerModel> c = repository.findByCpf(customer.getCpf());
        if(c.isEmpty()){
            CustomerModel newCustomer = new CustomerModel();
            BeanUtils.copyProperties(customer, newCustomer);
            return repository.save(newCustomer);
        }
        throw new KeyAlreadyExistsException("Customer already exist");
    }

    public String login(String email){
        Optional<CustomerModel> customer = repository.findByEmail(email);
        if(customer.isPresent()){
            return "TOKEN XXXXX";
        }
        throw new NoSuchElementException("Customer not found");
    }
}
