package com.ecommerce.ecommercejpa.customer;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommercejpa.customer.dto.CustomerResponse;

@Service
public class CustomerService {
    
    private final CustomerRepository repository;
    
    private final PasswordEncoder enconder;

    public CustomerService(CustomerRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.enconder = encoder;
    }

    public CustomerModel getCustomerByCpf(String cpf){
        Optional<CustomerModel> c = repository.findByCpf(cpf);
        if(c.isPresent()){
            return c.get();
        }
        throw new NoSuchElementException("Customer not found");
    }

    public CustomerResponse createCustomer(CustomerModel customer){
        Optional<CustomerModel> c = repository.findByCpf(customer.getCpf());
        if(c.isEmpty()){
            CustomerModel newCustomer = new CustomerModel();
            BeanUtils.copyProperties(customer, newCustomer);
            newCustomer.setPassword(this.encryptPassword(newCustomer.getPassword()));
            return this.customerDataToResponse(repository.save(newCustomer));
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

    public String encryptPassword(String password){
        return enconder.encode(password);
    }

    public CustomerResponse customerDataToResponse(CustomerModel customerData){
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customerData, customerResponse);
        return customerResponse;
    }
}
