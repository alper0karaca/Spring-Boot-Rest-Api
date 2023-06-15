package com.alperkaraca.CrudExample.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alperkaraca.CrudExample.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
