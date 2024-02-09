package com.ecommerce.ecommercejpa.customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommercejpa.Role.Role;
import com.ecommerce.ecommercejpa.Role.RoleEnum;
import com.ecommerce.ecommercejpa.customer.dto.CustomerRegisterRequest;
import com.ecommerce.ecommercejpa.customer.dto.CustomerResponse;

@Service
@Configurable
public class CustomerService implements  UserDetailsService{
    
    private final CustomerRepository repository;
    private final PasswordEncoder enconder;

    public CustomerService(CustomerRepository repository, PasswordEncoder enconder){
        this.repository = repository;
        this.enconder = enconder;
    }

    public Customer getCustomerByCpf(String cpf){
        Optional<Customer> c = repository.findByCpf(cpf);
        if(c.isEmpty()){
            throw new NoSuchElementException("Customer not found");
        }
        return c.get();
    }

    public Customer getCustomerByUsername(String username){
        Optional<Customer> c = repository.findByUsername(username);
        if(c.isEmpty()){
            throw new NoSuchElementException("Customer not found");
        }
        return c.get();
    }

    public CustomerResponse createCustomer(CustomerRegisterRequest customer){
        Optional<Customer> c = repository.findByCpf(customer.getCpf());

        if(c.isPresent()){
            throw new KeyAlreadyExistsException("Customer already exist");
        }

        Customer newCustomer = new Customer();
        BeanUtils.copyProperties(customer, newCustomer);
        customer.getRoles().forEach(role ->
            newCustomer.getRole().add(new Role(RoleEnum.valueOf(role.toUpperCase()).getValue(), role))
        );
        newCustomer.setPassword(this.encryptPassword(newCustomer.getPassword()));

        return this.customerDataToResponse(repository.save(newCustomer));
    }

    public String encryptPassword(String password){
        return enconder.encode(password);
    }

    public CustomerResponse customerDataToResponse(Customer customerData){
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customerData, customerResponse);
        return customerResponse;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> user = repository.findByEmail(email);
        
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User with email not found");
        }

        Customer customer = user.get();
        List<GrantedAuthority> authorities = customer.getRole().stream().map(role->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
        customer.setAuthorities(authorities);

        return customer;
    }
}
