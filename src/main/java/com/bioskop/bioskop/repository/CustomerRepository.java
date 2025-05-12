package com.bioskop.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

}
