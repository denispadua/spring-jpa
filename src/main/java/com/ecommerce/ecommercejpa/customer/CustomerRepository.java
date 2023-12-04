package com.ecommerce.ecommercejpa.customer;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;


public interface CustomerRepository extends ListCrudRepository<Customer, Long>{

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);
}
