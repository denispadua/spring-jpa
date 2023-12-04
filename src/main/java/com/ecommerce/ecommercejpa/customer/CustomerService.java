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

import com.ecommerce.ecommercejpa.Role.RoleEnum;
import com.ecommerce.ecommercejpa.Role.RoleModel;
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
            customer.getRoles().forEach(role ->{
                newCustomer.getRole().add(new RoleModel(RoleEnum.valueOf(role.toUpperCase()).getValue(), role));
            });
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
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<CustomerModel> user = repository.findByEmail(email);
        CustomerModel c = user.get();
        List<GrantedAuthority> authorities = c.getRole().stream().map(role->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
        c.setAuthorities(authorities);
        return c;
    }
}
