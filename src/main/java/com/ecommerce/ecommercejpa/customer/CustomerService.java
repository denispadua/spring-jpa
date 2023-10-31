package com.ecommerce.ecommercejpa.customer;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommercejpa.customer.dto.CustomerResponse;

@Service
@Configurable
public class CustomerService implements  UserDetailsService{
    
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

    public CustomerResponse createCustomer(CustomerModel customer){
        Optional<CustomerModel> c = repository.findByCpf(customer.getCpf());
        if(c.isEmpty()){
            CustomerModel newCustomer = new CustomerModel();
            BeanUtils.copyProperties(customer, newCustomer);
            // newCustomer.setPassword(this.encryptPassword(newCustomer.getPassword()));
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

    // public String encryptPassword(String password){
    //     return enconder.encode(password);
    // }

    public CustomerResponse customerDataToResponse(CustomerModel customerData){
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customerData, customerResponse);
        return customerResponse;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<CustomerModel> user = repository.findByEmail(email);
        return user.get();
    }
}
