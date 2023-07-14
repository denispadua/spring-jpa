package com.ecommerce.ecommercejpa.customer;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public CustomerModel getCustomerByCpf(String cpf) throws NoSuchElementException{
        return repository.findByCpf(cpf).get();
    }

    public CustomerModel createCustomer(CustomerModel customer) throws Exception{
        try {
            this.getCustomerByCpf(customer.getCpf());
            throw new Exception("User with this cpf already exists");
        } catch (NoSuchElementException e) {
            CustomerModel newCustomer = new CustomerModel();
            BeanUtils.copyProperties(customer, newCustomer);
            return repository.save(newCustomer);
        }
    }

    public String getCustomerByEmail(String email) throws NoSuchElementException{
        CustomerModel customer = repository.findByEmail(email).get();
        return "Bearer askjdoijwiouhuhwehwuiahewa";
    }
}
