package com.ecommerce.ecommercejpa.customer;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;


public interface CustomerRepository extends ListCrudRepository<CustomerModel, Long>{

    Optional<CustomerModel> findByCpf(String cpf);

    Optional<CustomerModel> findByEmail(String email);
}
